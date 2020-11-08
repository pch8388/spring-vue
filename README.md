spring boot + vue project

## local mail server 설정
- 로컬에서 메일을 전송하지 않고 테스트하기 위해 python 의 smtpd 를 사용하여 메일을 실제로 전송하지 않고 콘솔에 출력하도록 설정한다
```shell script
python -m smtpd -n -c DebuggingServer localhost:1025
```
- application.properties 에서 해당 포트로 연결
```yaml
spring:
  mail:
    host: localhost
    port: 1025
    properties:
      mail.smtp.auth: false
```