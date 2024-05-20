import streamlit as st
import pandas as pd
from fuzzywuzzy import process
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from sklearn.feature_extraction.text import CountVectorizer
import string
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from streamlit_chat import message
import numpy as np
# Load dataset
df = pd.read_csv('wine_data_with_predictions_v1.csv')

selected_columns = ['title', 'country', 'description', 'points', 'price',]
# Custom function to get user input based on input type
def get_user_input(input_type, label="", key_suffix=""):
    key = f"{input_type}_{key_suffix}"  # Generate unique key
    if input_type == 'text_input':
        return st.text_input(label, key=key)
    elif input_type == 'selectbox':
        return st.selectbox(label, df['country'].unique(), key=key)
    elif input_type == 'slider_point':        
        min_value = df['points'].min() 
        max_value = df['points'].max()        
        st.session_state['min_points'] =  st.slider('Select min points', min_value, max_value, min_value,key=key)
        st.write(st.session_state['min_points'])
    elif input_type == 'slider_price':
        min_value = df['price'].str.replace('$', '').astype(int).min()
        max_value = df['price'].str.replace('$', '').astype(int).max()        
        st.session_state['max_price'] =  st.slider('Select max price', min_value, max_value, min_value,key=key)
        st.write(st.session_state['max_price'])
        

# Streamlit app configuration
st.set_page_config(
    page_title="WineChat",
    page_icon=":wine:"
)
# st.title("Wine Recommendation Chatbot")

# Initialize session state
if 'conversation_state' not in st.session_state:
    st.session_state['conversation_state'] = 'start'

# Retrieve conversation state from session state
conversation_state = st.session_state['conversation_state']

if conversation_state == 'start':
    st.title('Wine Recommendation Chatbot')
    message("Welcome to Wine Recommendation Chatbot! 😊 If you want to start chatting, please say 'hi'.")
    user_input = st.text_input('You:', '')

    if st.button('Send'):
        if user_input.lower() == 'hi':      
            st.session_state['conversation_state'] = 'ask_wine_type'
            message("Hi, nice to meet you! I will help you today to recommend some wine 🍷😊")
            message("What kind of wine do you want? (red / white / rose / sparkling)?")
            wine_type_input = get_user_input('text_input', 'You:', 'wine_type')
            
elif conversation_state == 'ask_wine_type':
    message("What kind of wine do you want? (red / white / rose / sparkling)?")
    wine_type_input = get_user_input('text_input', 'You:', 'wine_type')

    # Fuzzy matching to find similar words
    wine_types = ['red', 'white', 'rose', 'sparkling']
    match, score = process.extractOne(wine_type_input.lower(), wine_types)

    # Filter DataFrame based on similar words and display the result
    if score >= 80:
        filtered_wines = df[df['wine_type'].str.lower().isin([match])]
        if not filtered_wines.empty:
            message("Here are some recommendations for", match, "wine:")
            filtered_wines_selected = filtered_wines[selected_columns]
            st.dataframe(filtered_wines_selected)
            message("What country of wine do you want?")
            st.session_state['conversation_state'] = 'ask_country'
            st.session_state['wine_country'] = get_user_input('selectbox', 'Select country', 'wine_country')
            st.session_state['selected_wine_type'] = match  # Store selected wine type
        else:
            message("I'm sorry, I couldn't find any recommendations for", match, "wine.")
    else:
        message("I'm sorry, I can only recommend red, white, rose, or sparkling wine.")

elif conversation_state == 'ask_country':
    message("What country of wine do you want?")
    st.session_state['wine_country'] = get_user_input('selectbox', 'Select country', 'wine_country')
    message("Describe the taste you prefer in the wine.")
    user_taste = get_user_input('text_input', 'You:', 'wine_taste')
    st.session_state['conversation_state'] = 'ask_taste'
    
elif conversation_state == 'ask_taste':
    message("Describe the taste you prefer in the wine.")
    user_taste = get_user_input('text_input', 'You:', 'wine_taste')
    st.session_state['user_taste'] = user_taste
    message("What is your minimum point that you can allow for wine?")
    get_user_input('slider_point',key_suffix='wine_point')
    st.session_state['conversation_state'] = 'ask_min_points'

elif conversation_state == 'ask_min_points':
    message("What is your minimum point that you can allow for wine?")
    
    get_user_input('slider_point',key_suffix='wine_point')  
        
    message("What is your maximum budget for wine?")
    get_user_input('slider_price',key_suffix='wine_price')
    
    st.session_state['conversation_state'] = 'ask_max_price'

elif conversation_state == 'ask_max_price':
    message("What is your maximum budget for wine?")
    get_user_input('slider_price',key_suffix='wine_price')
    st.session_state['recommend_me'] = None  # Initialize recommend_me key
    st.button('와인 추천')
    # recommend_me = get_user_input('text_input', 'You:', 'recommend_me')
    st.session_state['conversation_state'] = 'recommend_wine'
    
elif conversation_state == 'recommend_wine':
    # message("Type 'Enter'")
    # recommend_me = get_user_input('text_input', 'You:', 'recommend_me')
    # recommend_me = st.session_state.get('recommend_me')  # Use get() method to handle None


    
    # if recommend_me and recommend_me.lower() == 'enter':
    selected_wine_type = st.session_state['selected_wine_type'] #Retrieve selected winetype
    country = st.session_state['wine_country']
    min_points = st.session_state['min_points']
    max_price = st.session_state['max_price']
    user_taste = st.session_state['user_taste']

    # st.write(selected_wine_type)
    # st.write(country)
    # st.write(min_points)
    # st.write(max_price)
    # st.write(user_taste)
    
    
    # Filter DataFrame based on user input
    fillter_condition = (df['country'] == country) & \
                     (df['wine_type'].str.lower() == selected_wine_type.lower()) & \
                     (df['points'] >= min_points) & \
                     (df['price'].str.replace('$', '').astype(int) <= max_price)
    # st.write(fillter_condition)
    filtered_df = df[fillter_condition]
    
    st.dataframe(filtered_df)
    
    if not filtered_df.empty:
        # 자카드 유사도 측정을 위한 CountVectorizer
        # all_values = filtered_df[['title','description','variety']].values.flatten()
        # # all_values = [str(value) for value in all_values if value]  # Remove empty values
        # if len(all_values) > 0:
        vectorizer = CountVectorizer()
        X = vectorizer.fit_transform(filtered_df['description'])
        user_taste_vectorized = vectorizer.transform([user_taste])
        
        # 유사도 계산
        jaccard_similarity = cosine_similarity(user_taste_vectorized, X).flatten()
        
        # 추천 와인 선택
        if len(jaccard_similarity) == len(filtered_df):
            filtered_df['similarity'] = jaccard_similarity
            filtered_df = filtered_df.sort_values(by='similarity', ascending=False)
        else:
            st.warning("Lengths of jaccard_similarity and filtered_df do not match.")

            # filtered_df['similarity'] = jaccard_similarity
            # filtered_df = filtered_df.sort_values(by='similarity', ascending=False)
        
            # filtered_df = filtered_df.drop(columns=['similarity'])
        # recommendation_index = jaccard_similarity.argmax()
        # recommendation_wine = filtered_df.loc[recommendation_index, 'title']
        # print("Recommendation based on Jaccard Similarity:", recommendation_wine)
        
        # 유사도 계산을 위해 TF-IDF 벡터 생성
        # all_values = filtered_df[['country', 'title', 'province', 'winery', 'description', 'variety']].values.flatten()
        # all_values = [str(value) for value in all_values if value]  # Remove empty values
        # if all_values:
        #     tfidf_vectorizer = TfidfVectorizer(stop_words='english')
        #     tfidf_matrix = tfidf_vectorizer.fit_transform(all_values)
            
        #     # 사용자 입력값을 TF-IDF로 변환
        #     user_input_tfidf = tfidf_vectorizer.transform([user_taste])
            
        #     # 유사도 계산
        #     # st.write(tfidf_matrix)
        #     # st.write(filtered_df.index)
        #     similarity_scores = cosine_similarity(user_input_tfidf, tfidf_matrix[filtered_df.index]).flatten()
            
        #     # 유사도가 높은 순서대로 정렬하여 결과 반환
        #     filtered_df['similarity'] = similarity_scores
        #     filtered_df = filtered_df.sort_values(by='similarity', ascending=False)
            
        #     # 유사도 컬럼 제거
        #     filtered_df = filtered_df.drop(columns=['similarity'])
        
    else:
        st.warning("There are no wines taste that you selected criteria.")
     # else:
     #    print("Filtered DataFrame is empty.")
         
            # st.subheader("Wine Recommendations")
    st.subheader("Top 5 Wine Recommendations")
    top5_wines = filtered_df.head(5)
    st.write(top5_wines[['title', 'points', 'price', 'country', 'description']])
    
    # Display random 5 wine recommendations from the remaining wines
    st.subheader("Random 5 Wine Recommendations")
    remaining_wines = filtered_df.iloc[5:]
    if len(remaining_wines) >= 5:
        random_indices = np.random.choice(remaining_wines.index, 5, replace=False)
        random_wines = remaining_wines.loc[random_indices]
        st.write(random_wines[['title', 'points', 'price', 'country', 'description']])
    else:
        st.warning("There are no non-empty descriptions to recommend.")
    # else:
    #     st.warning("There are no wines matching the selected criteria.")