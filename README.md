# Hybrid DB PoC

The goal of this project is  to display that the size of the database 
can be very small, since each Account is a self-contained entity and 
does not share anything with other accounts.

## Assets

Each Account can have multiple 1 -> 10^4 of assets. In order to
keep database scalable, manageable and clean all those assets are to be stored on S3.
Assets include, but not limited to Offercodebanks, Markers, Audience, Geo, Creatives. 
Every day those assets are to be destructured for live flights only and send to mail 
engine.

## Database

Since the account is a quite simple object with a lot of one too few relationships. We can 
avoid normalizations and table split and store it as a single document. The most apropriate 
and known to me way to do this is to use a Mongo Atlas, which is a managed service. This will remove the 
need to have the migrations, simplify the permission, since if everything is withing an account,
one gets access to its dependencies by default. Also, it simplifies the 
operations on the account, since everything is withing a single document - a DBA can immidiately see
what belongs to a given account and what does not. No more 10 joins to find what offrercodes belong to a given live 
flight.

## Usage

In order to run this you need 2 things

- AWS S3 access (file uploads). The app will pick creds from your AWS CLI. More on creds location in here https://docs.aws.amazon.com/credref/latest/refdocs/file-location.html
- Mongo DB access (database). You can either replace a connection string for with your local mongo in application.properties or I can create a user for you in my test cluster.
Note: you have to provide me with your IP for whitelisting (security reasons).

