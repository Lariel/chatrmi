# Chat utilizando RMI

## Instruções de execução:

Criar .java.policy com o seguinte conteúdo e salvar na /home (linux):

grant {
	
	permission java.security.AllPermission;
};

###### Compilar dentro da SRC: 
	javac *.java 
  

###### Ativar o rmiregistry
	rmiregistry &   //ativa e retorna o PID
  
  
###### Executar em terminais distindos

    java AppChatServidor <SEU_IP>
    java AppChatCliente <IP DO SERVIDOR> <SEU NOME>

  
###### Parar o rmiregistry:

	lsof -i:<porta_exception>  //retorna PID
	kill <PID>

###### Para saber o número IP da máquina:

	ifconfig ou /sbin/ifconfig ou hostmane -I


