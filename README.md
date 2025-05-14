Murilo Lugli – RM553624

# Quod – Challenge Sprint 2
## Modelo C4
### Contexto
 ![image](https://github.com/user-attachments/assets/d3649fe0-f0f1-4677-9a03-0f28755e4a56)



### Contêineres
 ![image](https://github.com/user-attachments/assets/12636b6c-bc72-42af-803e-c71d42c9a42a)


### Componentes
 ![image](https://github.com/user-attachments/assets/575a209e-c9b4-455e-9cb8-bdae3d03782a)


## Bibliotecas da aplicação
### 1 – Spring Boot Web
Criação de APIs REST e aplicações web.
### 2 – Spring Boot Test
Testes unitários e de integração.
### 3 – Reactor Core
Fundação reativa não bloqueante para a JVM.
### 4 – Thymeleaf
Renderização de páginas HTML no servidor.
### 5 – Metadata Extractor
Extração de metadados (como GPS, data/hora) de imagens.
### 6 – Bean Validation
Verificação de entrada de dados no controller ou DTOs.
### 7 – Spring Data MongoDB
Integração com MongoDB.
### 8 – Apache Commons Imaging
Análise e leitura de propriedades de imagens.

## Documentação resumida
### Front-end (webapp)
 ![image](https://github.com/user-attachments/assets/96904f46-c735-4a74-a66b-f3d4a689a987)

Há 3 funcionalidades de verificação (facial, biométrica e documentos)
![image](https://github.com/user-attachments/assets/68868cca-a8a0-4de4-9aa8-f148f0cc43df)

### Back-end 
É obrigatório o envio de duas imagens para iniciar a verificação.  
As duas imagens são recebidas pelo back-end que verifica se:  
•	O arquivo é .jpg ou .png  
•	O arquivo é menor que 20 megabytes  
•	O arquivo possui mais que 300x300 pixels  
•	Há metadados de data e hora nas duas  
•	Os metadados de data e hora das imagens diferem menos que 10 minutos  
•	Os metadados de localização das imagens diferem menos que o raio de 1 quilômetro  
Falhando em qualquer uma dessas validações o processo é invalidado.  
*Por conta da proteção do android para os metadados de localização, não o tornei obrigatório, ou seja, se não houver metadados de localização, não é invalidado.\
  
  ![image](https://github.com/user-attachments/assets/da92d715-f232-49df-8167-b0e0030f7ee6)
  ![image](https://github.com/user-attachments/assets/a8039e2e-a79f-433e-8f99-c9ab96b8f9c2)

Caso seja detectado fraude, é enviado uma notificação HTTP para a url:  
/api/notificacoes/fraude
![image](https://github.com/user-attachments/assets/5e58783b-8c6c-45ad-9896-89cd2dee0a9b)
![image](https://github.com/user-attachments/assets/74b2f3a1-48af-4104-9e0a-6042e760429b)


 
 






### Database
Todos os processos são salvos na coleção ‘verifications’ com o padrão:  
_id: 6823b49b9334e506d70347ce  
tipo: "documento"  
data: 2025-05-13T21:07:39.816+00:00  
fraude: true  
imagem1Metadata: Object  
imagem2Metadata: Object  
![image](https://github.com/user-attachments/assets/208d7505-8cad-4e77-8888-85a5adc4157a)  

 

Quando é detectado fraude no processo, ele é salvo na coleção ‘notifications’ também, porém em um padrão com mais detalhes:

transacaoId: "a40ccd3e-b840-4f20-94bf-117c25bc5b73"  
tipoBiometria: "documento"  
tipoFraude: "metadados-divergentes"  
dataCaptura: "Tue May 13 17:36:39 BRT 2025"  
notificadoPor: "sistema-de-monitoramento"  
canalNotificacao: ["sms", "email"]  
metadados: Object  
	latitude: 0  
	longitude: 0
