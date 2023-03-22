#!/usr/bin/env python
# coding: utf-8

import warnings
warnings.filterwarnings(action='ignore')

import requests
# 이번에는 json 라이브러리가 아닌 xml 라이브러리를 이용해서 xml 파싱을 해보자.
from xml.etree.ElementTree import fromstring, ElementTree
from elasticsearch import Elasticsearch
es = Elasticsearch('http://localhost:9200/')

# 23년 3월 22일 현재 공공 와이파이 개수는 27911개 인데 오픈 API에서는 한 번에 호출할 수 있는 개수가 제한되서 1000개씩
# 나눠서 API를 호출한다.
for i in range(1, 29):
    iStart = (i - 1) * 1000 + 1
    iEnd = i * 1000
    # print('{:5d}, {:5d}'.format(iStart, iEnd))
    # 오픈 API 요청 주소를 만든다.
    targetSite = 'http://openapi.seoul.go.kr:8088/746e4968796d6b7639366763624a53/xml/TbPublicWifiInfo/{}/{}/'.format(iStart,
                            iEnd)
    # print(targetSite)
    
    response = requests.get(targetSite) # 오픈 API를 요청한다.
    tree = ElementTree(fromstring(response.text)) # 오픈 API가 응답한 내용을 XML 파싱한다.
    # print(i, tree)
    root = tree.getroot() # 파싱된 XML의 최상단 root 태그를 얻어온다.
    # print(root)
    
    # 파싱된 XML의 <row> ~ </row> 사이의 모든 데이터를 반복을 수행하며 얻어온다.
    for row in root.iter('row'):
        # print(row)
        # <row> ~ </row> 사이의 데이터 중에서 엘라스틱서치에 저장할 데이터만 가져온다.
        gu_nm = row.find('X_SWIFI_WRDOFC').text
        place_nm = row.find('X_SWIFI_MAIN_NM').text
        place_x = row.find('LAT').text
        place_y = row.find('LNT').text
        # print(gu_nm, place_nm, place_x, place_y)
        
        # <row> ~ </row> 사이에서 엘라스틱서치에 저장하기 위해 가져온 데이터를 딕셔너리로 만든다.
        doc = {
            'gu_nm': gu_nm,
            'place_nm': place_nm,
            'instl_xy': {
                'lat': float(place_y),
                'lon': float(place_x)
            }
        }
        # print(doc)
        
        # 엘라스틱 서치에 인덱싱한다.
        try:
            response = es.index(index='seoul_wifi', body=doc)
            # print(doc)
        except:
            # pass
            print('error:', doc)
            doc = {
                'gu_nm': gu_nm,
                'place_nm': place_nm,
                'instl_xy': {
                    'lat': float(place_x),
                    'lon': float(place_y)
                }
            }
            response = es.index(index='seoul_wifi', body=doc) 
    print('END: {:5d}, {:5d}'.format(iStart, iEnd))
print('END')
