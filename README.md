# Chat utilizando RMI

## Instruções de execução:

Criar .java.policy com o seguinte conteúdo e salvar na /home (linux):

> grant {
>		permission java.security.AllPermission;
> };

###### Compilar dentro da SRC: 
	javac clienteGui/*java
	javac servidorGui/*java
   
###### Executar em terminais distindos

	java servidorGui.Main 
	java clienteGui.Main

  
###### Parar o rmiregistry:

	lsof -i:<porta_exception>  //retorna PID
	kill <PID>

###### Para saber o número IP da máquina:

	ifconfig ou /sbin/ifconfig ou hostmane -I


## Lista de requisitos:
- [x] Cada cliente pode registrar uma lista de contatos;
- [x] Só é possível enviar mensagens para contatos registrados na lista de contatos;
- [x] Armazenar histórico de mensagens enviadas e recebidas para cada contato;
- [ ] Mostrar informação se a mensagem foi lida/exibida (a mensagem é lida quando o destinatário listar as mensagens de um determinado contato);
- [X] Criar grupos com contatos, com nome e lista de contatos participantes (máximo 8);
