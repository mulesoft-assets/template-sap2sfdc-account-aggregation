
# Anypoint Template: Salesforce and SAP Account Aggregation

This template aggregates (collects) accounts from Salesforce and customers from SAP into a CSV file. This basic pattern can be modified to collect from more or different sources and to produce formats other than CSV. The application can be triggered either manually or programmatically by an HTTP call. Accounts are sorted such that the accounts only in Salesforce appear first, followed by customers only in SAP, and lastly by accounts found in both systems. The custom sort or merge logic can be easily modified to present the data as needed. This template also serves as a great base for building APIs using the Anypoint Platform.

Aggregation templates can be easily extended to return a multitude of data in mobile friendly form to power your mobile initiatives by providing easily consumable data structures from otherwise complex backend systems.

![44e5ded5-9af8-44c2-9292-d79ebac89019-image.png](https://exchange2-file-upload-service-kprod.s3.us-east-1.amazonaws.com:443/44e5ded5-9af8-44c2-9292-d79ebac89019-image.png)

**Note:** Any references in the video to DataMapper have been updated in the template with DataWeave transformations.

```
![](https://www.youtube.com/embed/Zb2yGo0QlwI?wmode=transparent)
```

# License Agreement

This template is subject to the conditions of the [MuleSoft License Agreement](https://s3.amazonaws.com/templates-examples/AnypointTemplateLicense.pdf "MuleSoft License Agreement").

Review the terms of the license before downloading and using this template. You can use this template for free with the Mule Enterprise Edition, CloudHub, or as a trial in Anypoint Studio.

# Use Case

I want to aggregate accounts from Salesforce and customers from SAP and compare them to see which ones can only be found in one of the two systems and which ones are in both.

This template:

- Generates its results as a CSV report that is sent by email to the addresses you configure.
- Extracts data from two systems, aggregates the data, compares the values of the fields for the objects, and generates a report on the differences.
- Gets accounts from Salesforce and customers from SAP using standard BAPI BAPI\_CUSTOMER\_GETLIST, compares them by name, and generates a CSV file that shows the Salesforce account, customer in SAP, and accounts in Salesforce and SAP. The report is then emailed to a configured group of email addresses.

# Considerations

To make this template run, there are certain preconditions that must be considered.

All of them deal with the preparations in both systems (SAP and SFDC), that must be made in order for all to run smoothly.

Failing to do so could lead to unexpected behavior of the template.

## Disclaimer

This template uses a few private Maven dependencies from MuleSoft to work. If you intend to run this template with Maven support, you need to add three extra dependencies for SAP in the pom.xml file.

## SAP Considerations

Here's what you need to know to get this template to work with SAP.

### As a Data Source

SAP backend system is used as data source for aggregation.

Data is read by RFC call of BAPI function to SAP.

SAP Connector needs to be properly customized as "Properties to Configure".

## Salesforce Considerations

Here's what you need to know about Salesforce to get this template to work.

### FAQ

- Where can I check that the field configuration for my Salesforce instance is the right one? See: [Salesforce: Checking Field Accessibility for a Particular Field](https://help.salesforce.com/HTViewHelpDoc?id=checking_field_accessibility_for_a_particular_field.htm&language=en_US "Salesforce: Checking Field Accessibility for a Particular Field")
- Can I modify the Field Access Settings? How? See: [Salesforce: Modifying Field Access Settings](https://help.salesforce.com/HTViewHelpDoc?id=modifying_field_access_settings.htm&language=en_US "Salesforce: Modifying Field Access Settings")

### As a Data Destination

There are no considerations with using Salesforce as a data destination.

# Run it!

Simple steps to get Salesforce and SAP Account Aggregation running.

## Running On Premises

In this section we help you run your template on your computer.

### Where to Download Anypoint Studio and the Mule Runtime

If you are a newcomer to Mule, here is where to get the tools.

- [Download Anypoint Studio](https://www.mulesoft.com/platform/studio)
- [Download Mule runtime](https://www.mulesoft.com/lp/dl/mule-esb-enterprise)

### Importing a Template into Studio

In Studio, click the Exchange X icon in the upper left of the taskbar, log in with your

Anypoint Platform credentials, search for the template, and click **Open**.

### Running on Studio

After you import your template into Anypoint Studio, follow these steps to run it:

- Locate the properties file `mule.dev.properties`, in src/main/resources.
- Complete all the properties required as per the examples in the "Properties to Configure" section.
- Right click the template project folder.
- Hover your mouse over `Run as`
- Click `Mule Application (configure)`
- Inside the dialog, select Environment and set the variable `mule.env` to the value `dev`
- Click `Run`To make this template run on Studio, there are a few extra steps that needs to be made.Search for "Enabling Your Studio Project for SAP" at `https://docs.mulesoft.com`.

### Running on Mule Standalone

Complete all properties in one of the property files, for example in mule.prod.properties and run your app with the corresponding environment variable. To follow the example, this is `mule.env=prod`. 

## Running on CloudHub

While creating your application on CloudHub (or you can do it later as a next step), go to Runtime Manager > Manage Application > Properties to set the environment variables listed in "Properties to Configure" as well as the **mule.env**.

### Deploying your Anypoint Template on CloudHub

Studio provides an easy way to deploy your template directly to CloudHub, for the specific steps to do so check this

## Properties to Configure

To use this template, configure properties (credentials, configurations, etc.) in the properties file or in CloudHub from Runtime Manager > Manage Application > Properties. The sections that follow list example values.

### Application Configuration

**HTTP Connector Configuration**

- http.port `9090`        

**SAP Connector Configuration**

- sap.jco.ashost `your.sap.address.com`
- sap.jco.user `SAP_USER`
- sap.jco.passwd `SAP_PASS`
- sap.jco.sysnr `14`
- sap.jco.client `800`
- sap.jco.lang `EN`

**SalesForce Connector Configuration**

- sfdc.username `bob.dylan@sfdc`
- sfdc.password `DylanPassword123`
- sfdc.securityToken `avsfwCUl7apQs56Xq2AKi3X`

**SMTP Services Configuration**

- smtp.host `smtp.example.com`
- smtp.port `587`
- smtp.user `examplemailuser`
- smtp.password `examplemailpassword`

**Email Details**

- mail.from `your.email@example.com`
- mail.to `your.email@example.com`
- mail.subject `Mail subject`
- mail.body `Aggregation Report`
- attachment.name `aggregation_report.csv`

# API Calls

Salesforce imposes limits on the number of API calls that can be made. However, this template

makes an API call to Salesforce only once during aggregation.

# Customize It!

This brief guide intends to give a high level idea of how this template is built and how you can change it according to your needs.

As Mule applications are based on XML files, this page describes the XML files used with this template.

More files are available such as test classes and Mule application files, but to keep it simple, we focus on these XML files:

- config.xml
- businessLogic.xml
- endpoints.xml
- errorHandling.xml

## config.xml

Configuration for connectors and configuration properties are set in this file. Even change the configuration here, all parameters that can be modified are in properties file, which is the recommended place to make your changes. However if you want to do core changes to the logic, you need to modify this file.

In the Studio visual editor, the properties are on the _Global Element_ tab.

## businessLogic.xml

The functional aspect of this template is implemented on this XML, directed by a flow responsible for conducting the aggregation of data, comparing records, and formatting the output in this case a report.

Using the Scatter-Gather component this template queries the data in different systems. After that, the aggregation is implemented in a DataWeave 2 script using the Transform component.

Aggregated results are sorted by source of existence:

1. Accounts only in Salesforce.
2. Accounts only in SAP (customers).
3. Accounts in both Salesforce and SAP (customers) and transformed to CSV format. The final report in CSV format is sent to the email addresses that you configured in the  mule.\*.properties file.

## endpoints.xml

The file with the endpoint that starts the aggregation. This template uses an HTTP Listener to trigger the use case.

### Trigger Flow

**HTTP Inbound Endpoint** - Start Report Generation

- `${http.port}` is set as a property to be defined either on a property file or in CloudHub environment variables.
- The path configured by default is `generatereport` that you are free to change to the one you prefer.
- The host name for all endpoints in your CloudHub configuration should be defined as `localhost`. CloudHub then routes requests from your application domain URL to the endpoint.

## errorHandling.xml

This is the right place to handle how your integration reacts depending on the different exceptions.

This file provides error handling that is referenced by the main flow in the business logic.

