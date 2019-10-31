export API_KEY=$(aws ssm get-parameters --name /${ENV}/api-jva-openapi-example/api-key --with-decryption --region eu-west-1 --query Parameters[0].Value --output text)


java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar