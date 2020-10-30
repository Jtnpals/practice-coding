from pymongo import MongoClient

client = MongoClient('localhost', 32768)
db = client.dbjungle

star = db.movies.find_one({'title': '매트릭스'})['star']
print(star) #9.39

sameStarTitle = list(db.movies.find({'star': star}))
for movie in sameStarTitle:
    print(movie['title'])


db.movies.update_one({'title': '매트릭스'}, {'$set': {'star': 0}})
new = db.movies.find_one({'title': '매트릭스'})['star']
print(new)