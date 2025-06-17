
Base:
Mysql DB
Liquabase for migrations
Expose elide APIs for all tables
User:
Table
User
User_Notifications
On Student creation produce an event
Login API
Authenticated User Creation API
Courses:
Tables
courses
user_courses
On user creation internal api consume it and enqueues a job to create a user_courses records
When user_course is created, create a user_notification records in user_notifications table
Use Redis to not allow creation of more than one course in 24 hours



both are microservices