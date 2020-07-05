from flask import Flask, jsonify, render_template, request
from flask_cors import CORS
from flask_sqlalchemy import SQLAlchemy
import json

app = Flask(__name__)
CORS(app, resources=r'/*')  # 允许跨域处理
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql+pymysql://root:232419@127.0.0.1:3306/xiaoxueqi'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config['SQLALCHEMY_ECHO'] = True
db = SQLAlchemy(app)


# ORM
class Forecast(db.Model):
    __tablename__ = 'forecast'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(20))
    day1 = db.Column(db.Float)
    day2 = db.Column(db.Float)
    day3 = db.Column(db.Float)
    day4 = db.Column(db.Float)
    day5 = db.Column(db.Float)
    day6 = db.Column(db.Float)
    day7 = db.Column(db.Float)

    def __repr__(self):
        return '<Location %r>' % self.name


# app.config['SQLALCHEMY_DATABASE_URI'] ='mysql://useword:password@127.0.0.1:3306/db_demo2?charset=utf8'
@app.route('/api/weather')
def weather():
    t = request.args.get('name')
    # data = json.loads(request.body.decode('utf-8'))
    object = Forecast.query.filter_by(name=t).first()
    if object is None:
        return jsonify({'code': 0, 'msg': "获取失败！"})
    else:
        data = [object.day1, object.day2, object.day3, object.day4, object.day5, object.day6, object.day7]
        return jsonify({'code': 1, 'data': data})


if __name__ == '__main__':
    app.run(port=8000, debug='true')  # host是地址
