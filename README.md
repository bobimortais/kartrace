# Projeto Kart Race
Projeto para exibição do resultado de uma corrida de kart após o processamento de um arquivo de log
correspondente. O projeto foi desenvolvido em Java 8, com a utilização do framework JUnit para testes
automatizados

# Testando o projeto
Pré-requisitos
  - Java 1.8+
  - Maven 3+

## Rodando a aplicação:
  - Abra a linha de comando
  - Vá para a pasta raiz do projeto
  - Na linha de comando, execute **mvn clean package** para a realização do build
  - Após a conclusão do build, vá para a pasta **target** do projeto
  - Na linha de comando, execute **java -jar kartrace-1.0-SNAPSHOT-jar-with-dependencies.jar "Caminho completo do arquivo de log"**
  - Após a execução do comando acima, o resultado da corrida deverá ser exibido no terminal

## Execução de testes automáticos:
  - Abra a linha de comando
  - Vá para a pasta raiz do projeto
  - Na linha de comando, execute **mvn test**
  - Verifique os resultados dos testes executados