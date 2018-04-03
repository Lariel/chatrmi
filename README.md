Chat utilizando RMI
Instruções de execução:

Criar .java.policy com o seguinte conteúdo:

  grant {
   permission java.security.AllPermission;
  };
  
Compilar:
  javac ChatServer.java
  javac ChatClient.java
  rmic ChatImpl

Ativar o rmiregistry 
  rmiregistry &   //ativa e retorna o PID

Parar o rmiregistry:
  lsof -i:<porta_exception>  //retorna PID
  kill <PID>


Em um dos terminais:
  java ChatServer <ipdamaquina>

Em outro terminal:
  java ChatClient <ipdamaquinadoservidor>

Para saber o número IP da máquina:
  ifconfig ou 
  /sbin/ifconfig ou 
  hostmane -I
