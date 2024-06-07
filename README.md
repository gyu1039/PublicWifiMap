# PublicWifiMap

> 공공 와이파이 정보를 통해 내 위치에서 가장 가까운 와이파이를 알려주는 프로젝트


## 개발 환경
+ Java8
+ Maven
+ Servlet/Jsp
+ Tomcat
+ sqlite
+ Intellij
+ [OpenAPI](https://data.seoul.go.kr/dataList/OA-20883/S/1/datasetView.do)


## 파일 설명
* /data
  * API : open api를 요청하는 클래스
  * DB : db 연결과 CRUD 역할을 맡는 클래스
  * History: 위치 히스토리 정보를 담는 클래스
  * JsonResult: api 요청 결과를 받는 클래스
  * Row: 와이파이 정보
  * TbPublicWifiInfo: api 요청 결과를 받는 클래스2
  * WifiInfoWithDistance: 와이파이 정보 + 거리를 나타내는 데이터
* /servlet
  * history: 위치 히스토리 요청(get, post, delete)
  * NearByWifiServlet: 현재 위치에서 가까운 와이파이의 위치
  * OpenAPIListServlet: api 요청



