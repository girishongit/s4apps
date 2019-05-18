This provides and authentication service, using which we can authentication our application

approuter/xs-app.json hold the routes too be taken upon authorization,

it can includes scope if provided.

Sample app json can be of the type

{
"welcomeFile": "index.html",
"routes": [
{
"source": "/sap/ui5/1(.*)",
"target": "$1",
"localDir": "sapui5"
},
{
"source": "/rest/addressbook/testdataDestructor",
"destination": "backend",
"scope": "$XSAPPNAME.Display"
},
{
"source": "/rest/.*",
"destination": "backend"
},
{
"source": "^/(.*)",
"localDir": "resources"
}
]
}

to disable csrf and authentication:

"csrfProtection": true,
"authenticationType": "xsuaa", //none

after all this destination are defined in mainfest.yml along with url to be redirected

Then there should be package.json specifying the dependency of "@sap/approuter" and the start script if "node node_modules/@sap/approuter/approuter.js"

NOw create an XSUAA service on the cloud, if doesnt exist using your xs-security.json
cf create-service xsuaa application xsuaa-authentication -c xs-security.json
//cf update-service xsuaa-authentication -c xs-security.json

one done install npm dependencies and push to cloud:

using command cf push <Application-name> -f <mainfest.yml path>
eg. xsuaa>> cf push cg-pbs-approuter -f mainfest.yml
