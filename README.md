## OpenId simple server

A very partial implementation of OpenidConnect authorization code flow, for tests purposes.
It consists in three endpoints : 
* /authorize (GET) : redirect to requesting url (redirect_url) passing a code parameter
* /token (POST) : returns a JWT token for an authorization code
* /userinfo (POST) : returns userinfo data




