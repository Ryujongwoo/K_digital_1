#!/usr/bin/env python
# coding: utf-8

import warnings
warnings.filterwarnings(action='ignore')

import requests
import json
import xmltodict # xml 데이터를 딕셔너리로 변환하기 위해 import 한다.

targetSite = 'http://openapi.seoul.go.kr:8088/746e4968796d6b7639366763624a53/xml/TbPublicWifiInfo/1/1/'
response = requests.get(targetSite) # 공공 와이파이 정보를 open API로 요청해서 xml 데이터로 받는다.
# print(response)
# print(response.text) # xml 형태의 결과가 보여진다.
dictionary = xmltodict.parse(response.text) # xml 형태의 데이터를 딕셔너리 형태로 변환한다.
# print(dictionary)
# 딕셔너리를 json 타입의 데이터로 변환하고 ensure_ascii=False 옵션을 지정해서 한글 깨짐을 방지한다.
json_object = json.dumps(dictionary, ensure_ascii=False, indent=2)
print(json_object)
