#!/usr/bin/env python
# coding: utf-8

import warnings
warnings.filterwarnings(action='ignore')

from elasticsearch import Elasticsearch # 파이썬에서 elasticsearch를 사용하기 위해 import 한다.
# elasticsearch 객체를 만든다.
es = Elasticsearch('http://localhost:9200/')

# elasticsearch에 인덱싱할 도큐먼트를 만든다.
doc = {'name': 'kim', 'age': 35}
# elasticsearch 객체의 index() 함수의 index 속성에 생성할 인덱스 이름, id 속성에 id, body 속성에 인덱싱할 도큐먼트를
# 지정하고 도큐먼트를 인덱싱한다.
response = es.index(index='test_index', id=1, body=doc)
print(response)
print('=' * 80)

# elasticsearch 객체의 search() 함수에 사용할 DSL 쿼리를 만든다.
doc = {
    "size": 1,
    "query": {
        "term": {
            "DestCityName": {
                "value": "Seoul"
            }
        }
    }
}

# search() 함수의 index 속성에 도큐먼트를 읽어올 인덱스 이름, doc 속성에 DSL 쿼리를 지정해서 elasticsearch에
# 데이터를 요청한다.
response = es.search(index='kibana_sample_data_flights', body=doc)
print(response)
