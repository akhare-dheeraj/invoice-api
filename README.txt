Deployment Instructions:

1. Requirements:
	Java 8
	Maven
	Apache Tomcat 8.5.31
	Windows 10
2. After extracting folder in the root directory of the folder run below command:
	mvn clean package install tomcat:run
3. api will be deployed on base url http://localhost:8080/invoice-api


Instructions for accessing the API:
1. Two resources are provided
	/invoices	CREATE	UPDATE	FETCH
	/items	CREATE	FETCH
	
****************INVOICES******************
For  creating  invoices
Request:
curl -X POST \
  http://localhost:8080/invoice-api/invoices/create \
  -H 'Accept: application/json' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 4914c15c-7bf7-4f24-af32-9313375bd39a' \
  -d '{
  "invoiceItem": [
    {
      "item_name": "Jeans",
      "item_category": "NON_MEDICAL",
      "tax_rate": 20,
      "price": 1600,
      "quantity": 5
    },
    {
      "item_name": "Smartphone",
      "item_category": "NON_MEDICAL",
      "tax_rate": 30,
      "price": 40000,
      "quantity": 10
    }
  ]
}'
Response:
{
    "invoice_id": 1,
    "invoiceItems": [
        {
            "invoice": {
                "invoice_id": 1,
                "item_name": "Jeans",
                "item_category": "NON_MEDICAL",
                "tax_rate": 20,
                "price": 1600,
                "quantity": 5
            },
            "total_tax": 1600,
            "total_price": 8000,
            "selling_price": 9600
        },
        {
            "invoice": {
                "invoice_id": 2,
                "item_name": "Smartphone",
                "item_category": "NON_MEDICAL",
                "tax_rate": 30,
                "price": 40000,
                "quantity": 10
            },
            "total_tax": 120000,
            "total_price": 400000,
            "selling_price": 520000
        }
    ],
    "total_tax": 121600,
    "total_price": 408000,
    "selling_price": 529600,
    "created_date": 1533488630674,
    "last_modified": 1533488630674
}

________________________________________________________________________________________________________________________
For fetching invoice by id:
Request:
curl -X GET \
  http://localhost:8080/invoice-api/invoices/get/1 \
  -H 'Accept: application/json' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: b1f4a395-ff01-4f8b-b90b-dee7c3b57647'
  
 Response:
 {
    "invoice_id": 1,
    "invoiceItems": [
        {
            "invoice": {
                "invoice_id": 1,
                "item_name": "Jeans",
                "item_category": "NON_MEDICAL",
                "tax_rate": 20,
                "price": 1600,
                "quantity": 5
            },
            "total_tax": 1600,
            "total_price": 8000,
            "selling_price": 9600
        },
        {
            "invoice": {
                "invoice_id": 2,
                "item_name": "Smartphone",
                "item_category": "NON_MEDICAL",
                "tax_rate": 30,
                "price": 40000,
                "quantity": 10
            },
            "total_tax": 120000,
            "total_price": 400000,
            "selling_price": 520000
        }
    ],
    "total_tax": 121600,
    "total_price": 408000,
    "selling_price": 529600,
    "created_date": 1533487065252,
    "last_modified": 1533487093934
}
________________________________________________________________________________________________________________________
For updating invoices:
Request:
curl -X PUT \
  http://localhost:8080/invoice-api/invoices/update/1 \
  -H 'Accept: application/json' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 8f3275fb-f325-4965-a6b5-e71fd1328103' \
  -d '{
  "invoiceItem": [
    {
    	"invoice_id": 1,
      "item_name": "Shirt",
      "item_category": "NON_MEDICAL",
      "tax_rate": 20,
      "price": 100,
      "quantity": 5
    },
    {
    	"invoice_id": 2,
      "item_name": "Mi",
      "item_category": "NON_MEDICAL",
      "tax_rate": 30,
      "price": 2000,
      "quantity": 15
    }
  ]
}'

Response:
{
    "invoice_id": 1,
    "invoiceItems": [
        {
            "invoice": {
                "invoice_id": 1,
                "item_name": "Shirt",
                "item_category": "NON_MEDICAL",
                "tax_rate": 20,
                "price": 100,
                "quantity": 5
            },
            "total_tax": 100,
            "total_price": 500,
            "selling_price": 600
        },
        {
            "invoice": {
                "invoice_id": 2,
                "item_name": "Mi",
                "item_category": "NON_MEDICAL",
                "tax_rate": 30,
                "price": 2000,
                "quantity": 15
            },
            "total_tax": 9000,
            "total_price": 30000,
            "selling_price": 39000
        }
    ],
    "total_tax": 9100,
    "total_price": 30500,
    "selling_price": 39600,
    "created_date": 1533488630674,
    "last_modified": 1533488652120
}


@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@


******ITEMS********

For creating items
Request:
curl -X POST \
  http://localhost:8080/invoice-api/items/create \
  -H 'Accept: application/json' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: f8f7bc27-c155-4c85-bb54-4de9c33ac56a' \
  -d '{
  "item": {
    "item_name": "Lifeboy",
    "tax_rate": 10,
    "price": 60,
    "item_category": "NON_MEDICAL"
  }
}'

Response:
{
    "items": [
        {
            "item_id": 4,
            "item_name": "Lifeboy",
            "tax_rate": 10,
            "price": 60,
            "item_category": "NON_MEDICAL"
        }
    ]
}

For fetching all items:
Request:
curl -X GET \
  http://localhost:8080/invoice-api/items \
  -H 'Accept: application/json' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: bd4c9892-48bb-48d5-92c3-c6e009745b5c'
  
 Response:
 {
    "items": [
        {
            "item_id": 4,
            "item_name": "Lifeboy",
            "tax_rate": 10,
            "price": 60,
            "item_category": "NON_MEDICAL"
        }
    ]
}





