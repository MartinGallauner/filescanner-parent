# Filescanner


This application provides functionality to:
- Scan a specified folder, it's subfolders and files.
- Create a report of all scanned folders sorted by name.


##### Planned features:
- Create report of all scanned folders with aggregated file sizes.


#### How to run

- Define the path you want in the `docker-compose.yaml` on the left side of the volume property.
- Run `docker-compose up -d`


#### Available API

- POST /start
- POST /purge
- GET /report/folders
- GET /report/filesizes



