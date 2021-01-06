## team-22 - UMEED FOR WOMEN

# Problem Statement

Umeed stands for a cause that is something we as a team resonate with. We truly believe that the change that Umeed foundation is bringing in the lives of these women is commendable. A large number of these women were exploited due to their lack of knowledge of their own rights. So we decided to work for Umeed so that they can uplift more women, educate them, make them self-dependent.

# Our Solution:

The main objectives that we accomplished were: 1. An Admin at the top responsible for dividing the bulk orders into small tasks and assigning it to the managers via a website. 2. Distribution of work amongst women which can be tracked through an app by a manager(Umeed Personnel) 3. Feedback and uploading of images to track the progress of the production from the worker's(women) side.

# Technologies Used:

Mobile Application: Android
Backend - PHP REST APIs
Hosting - Heroku
Website - HTML,CSS,Javascript and Bootstrap

# Website:
for the front end web development, go to the folder react->LoginPage and run index.html file, it will load the landing page of the website. There are 3 folders namely css, js and images which contain cascading style sheets file used throughout the website, the js folder contains all the javacsripts file used throughout and the images folder has all the imges. by this implementation, the landing page opens which has about us , umeed , glimpse and login section. the admin can login to the admin page and can view the already assigned tasks and the events and can create event for the future. the admin  will create the task and assign  the manager the number of artefacts along with the quantity and the deadline. Admin will  get prompt after the successful creation of the task, and then can sign out. Front end and backend were successfully created but couldn't be linked due to API hit issues. The project is demonstrated using the dummy values.

# Android App:
A Manager of a particular group of women residing in the same or different locality can register women through the app and provide them login credentials. Now, after a Manager is assigned a particular task by the admin via the website, the managers can distribute the tasks on hand(i.e to develop artifacts) according to the deadline to the women. Now, during the weekdays when the NGO has no physical interaction with the women, the managers can track the daily progress of women through a feedback form filled by the women which contains a picture of their daily work and grievances if they face any problems. The managers could review the progress of the women and carry out the level 1 check.

# PHP REST APIs:
The PHP REST APIs act as an independent backend layer. So, at a given point of time if the NGO people want to switch from an android application to a mobile device, they won't have to change the backend much. All the API files are included in the php folder which contains the classes(which communicates with the database) and the other php files receive the "GET","POST",etc requests through the app as well as the website. The APIs are hosted on heroku and it's credentials are removed for security purposes.

# Future scope: 
We have implemented the basic prototypes of the progress bar which are mainly pictorial which will help in solving the language barrier issues. The front end and backend of the website could be integrated and then webiste could be successfully used by the admin. Progress bar could be added as an add on feature in the admin section to see the progress of the each manager using pie chart. excel to sql implementation will be easier since the app has been developed.



##### The code ("Code") in this repository was created solely by the student teams during a coding competition hosted by JPMorgan Chase Bank, N.A. ("JPMC").						JPMC did not create or contribute to the development of the Code.  This Code is provided AS IS and JPMC makes no warranty of any kind, express or implied, as to the Code,						including but not limited to, merchantability, satisfactory quality, non-infringement, title or fitness for a particular purpose or use.