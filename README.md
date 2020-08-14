# Simple-Batch-Platform

## Purpose
#### This is a batch-platform that can execute jar files in specific time and specific dependency order.
	
## Usage
**1. First, create jar files.**
- creating two jars (billsetuptask and billrun) from spring offcial document https://spring.io/guides/gs/spring-cloud-task in this project.

**2. Second, insert batches information like jar_name, execute_time, dependency into database base on there execution frequency. (daily basis, monthly basis, yearly basis)**

> INSERT INTO DAILY_BATCH(JAR_NAME, NOTE, HOUR, MINUTE, WAITING_JAR) VALUES('billsetuptask', 'create table BILL_STATEMENTS', '09', '00', null);

> INSERT INTO DAILY_BATCH(JAR_NAME, NOTE, HOUR, MINUTE, WAITING_JAR) VALUES('billrun', 'insert data to table BILL_STATEMENTS', '09', '00', 'billsetuptask');

- In above case, both batches billsetuptask and billrun are setting to be execute at 9AM everyday, but billrun will execute only after billsetuptask completed running.

3. Third, put your jar files in the directory which you set to 'batch.directory' parameter in application.properties.
4. Last, run the application and observe the running result through console log or online api.
