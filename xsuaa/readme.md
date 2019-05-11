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
"scope": "node-hello-world.Delete"
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

after all this destination are defined in mainfest.yml along with url to be redirected

Then there should be package.json specifying the dependency of "@sap/approuter" and the start script if "node node_modules/@sap/approuter/approuter.js"

one done install npm dependencies and push to cloud:

using command cf push <Application-name> -f <mainfest.yml path>
