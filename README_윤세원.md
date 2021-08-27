# 운영
 
- CI/CD 설정 

각 구현체들은 각자의 source repository 에 구성되었고, 각 소스 경로에서
mvn package 및 컨테이너 레지스트리 의 리파지토리로 이미지를 빌드한 후
미리 작성한 yml 파일을 통해 배포하였다.

 소스 가져오기
 
   git clone https://github.com/leo99k/carrental.git

 
- Deploy / Pipeline
 
  •	build 하기
 계약 경로로 접속
  cd Contract

  mvn package

   az acr build --registry user05 --image user05.azurecr.io/contract:v1 .

미리 생성한 yml 파일을 이용하여 배포
  kubectl apply -f deployment.yml
  kubectl apply -f service.yaml  //또는 kubectl expose deploy mypage --type=ClusterIP --port=8080 (gateway 의 경우는 LoadBalancer) 로 적용한다.

 * docker로 할 경우
   -- 도커 빌드
    docker build -t user05.azurecr.io/contract:v1 .
   -- 도커 푸시
    docker push user05.azurecr.io/contract:v1

   --권한 오류시
    az acr login --name user05

  각 Pay / Reservation / MyPage / gateway 도 동일하게 처리한다.

 Service, Pod, Deploy 상태 확인 
 
 ![image](https://user-images.githubusercontent.com/86760697/131059581-8f0d3cc8-ac9a-43e1-91cc-d3791710ad0e.png)

 
    
- ConfigMap
 •	deployment.yml 파일에 설정
```yaml
 ## Config map Set start
          env:
            - name: SYSTEM_MODE
              valueFrom:
                configMapKeyRef:
                  name: systemmode
                  key: sysmodeval
 ## Config map Set end

 ConfigMap 생성
   kubectl create configmap systemmode --from-literal=sysmodeval=PROD_SYSTEM
 Configmap 생성, 정보 확인
   kubectl get configmap systemmode -o yaml

![image](https://user-images.githubusercontent.com/86760697/131059863-865e36dc-e262-46bf-a47c-97d6c4c14f93.png)

 *Contract 호출 시 해당 시스템이 로컬/개발/운영 인지 여부를 ConfigMap 을 이용해서 확인하도록 구현하였음

'''
@PostPersist
    public void onPostPersist(){
    	
    	// configMap 테스트
        String sysMode = System.getenv("SYSTEM_MODE");
        if(sysMode == null) sysMode = "LOCAL_SYSTEM";
        System.out.println("################## 현재 접속중인 시스템 : " + sysMode);
    	
        System.out.println("################### Contract >> 계약 생성 ##############################");


*로컬의 경우

![image](https://user-images.githubusercontent.com/86760697/131060071-afc9937b-e51c-4535-ac55-e5cffbb22508.png)

*서버의 경우

![image](https://user-images.githubusercontent.com/86760697/131060095-d1809385-52f4-415e-a81c-d7835b059ceb.png)

```

    - [동기식 호출 / 서킷 브레이킹 / 장애격리](#동기식-호출-서킷-브레이킹-장애격리)
    
    
    
    - [오토스케일 아웃](#오토스케일-아웃)
    - [무정지 재배포](#무정지-재배포)
    - [Liveness Probe](#Liveness-Probe)












