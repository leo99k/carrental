# 렌트카계약(CarRental)

# Table of contents
- [렌트카](#---)
  - [서비스 시나리오](#서비스-시나리오)
  - [체크포인트](#체크포인트)
  - [분석/설계](#분석설계)
  - [구현:](#구현-)
    - [DDD 의 적용](#ddd-의-적용)
	- [GateWay적용](#GateWay-적용)
	- [CQRS/saga/correlation](#폴리글랏-퍼시스턴스)
    - [동기식 호출 과 Fallback 처리](#동기식-호출-과-Fallback-처리)
	- [폴리글랏 퍼시스턴스](#폴리글랏-퍼시스턴스)
	
  - [운영](#운영)
    - [CI/CD 설정](#cicd설정)
    - [Deploy / Pipeline](#Deploy-Pipeline)
    - [ConfigMap](#ConfigMap)		
    - [동기식 호출 / 서킷 브레이킹 / 장애격리](#동기식-호출-서킷-브레이킹-장애격리)
    - [오토스케일 아웃](#오토스케일-아웃)
    - [무정지 재배포](#무정지-재배포)
    - [Liveness Probe](#Liveness-Probe)
	


무정지 재배포 (Readiness Probe)
Readiness 설정 적용 전
서비스가 정상적으로 수행되고 있는지 확인을 위해 부하를 걸어준다.
```
siege -c1 -t120S -v --content-type "application/json" 'http://20.200.226.177:8080/contracts POST {"custName": "siege1", "modelName": "부하테스트", "amt": 150}'
```
Contrant 서비스의 deployment.yml 파일을 deployment_v2.yml 버전으로 생성한 후
kubectl apply -f deployment_v2.yml 로 버전을 변경하여 배포를 수행한다.

![image](https://user-images.githubusercontent.com/18524113/131059681-0d99b7dc-0281-4355-865d-df19db086eb7.png)

배포중 서비스 중단으로 인해 Availability 가 28% 임을 확인할 수 있다.
![image](https://user-images.githubusercontent.com/18524113/131059764-dc965941-565d-44c8-87b9-a295466f94b9.png)


Readiness 설정 적용 후
배포 파일에 설정을 적용한다.
```
readinessProbe:
  httpGet:
    path: '/actuator/health'
    port: 8080
  initialDelaySeconds: 10
  timeoutSeconds: 2
  periodSeconds: 5
  failureThreshold: 10
```
서비스가 정상적으로 수행되고 있는지 확인을 위해 부하를 걸어준다.
```
siege -c1 -t120S -v --content-type "application/json" 'http://20.200.226.177:8080/contracts POST {"custName": "siege1", "modelName": "부하테스트", "amt": 150}'
```
Contrant 서비스의 deployment.yml 파일을 deployment_v2.yml 버전으로 생성한 후
kubectl apply -f deployment_v2.yml 로 버전을 변경하여 배포를 수행한다.
배포중 서비스 중단이 없음을 확인할 수 있다.
![image](https://user-images.githubusercontent.com/18524113/131060195-bc5d5369-33e0-4f81-b1c8-17e2df12eed2.png)

![image](https://user-images.githubusercontent.com/18524113/131060217-d7dde57a-8bf3-4792-9765-a731d79a2482.png)

![image](https://user-images.githubusercontent.com/18524113/131060241-6edb73b5-ccd1-4a5d-adf2-ca4585a75f2d.png)

