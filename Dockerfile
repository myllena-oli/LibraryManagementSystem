FROM openjdk:11
COPY out/artifacts/library_jar/library.jar .
CMD ["java","-jar","library.jar"]
