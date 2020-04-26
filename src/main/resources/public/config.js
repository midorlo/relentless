// noinspection JSUnusedGlobalSymbols
export default {
    "name": "Relentless Mockup",
    "baseUrl": "http://localhost:8080/",
    "pages": [
        {
            "name": "Cell Types",
            "id": "cellTypes",
            "methods": {
                "getAll": {
                    "url": "/cellTypesSimple",

                    "display": {
                        "type": "cards"
                    },
                    "fields": [
                        {
                            "name": "name",
                            "type": "text",
                            "label": "Name"
                        }
                    ]
                }
            }
        },
        {
            "name": "Cells",
            "id": "cells",
            "methods": {
                "getAll": {
                    "label": "Get All",
                    "url": "/cells",
                    "display": {
                        "type": "table",
                        "fields": [
                            {
                                "name": "name",
                                "type": "text",
                                "label": "Name"
                            },
                            {
                                "name": "cellType",
                                "type": "object",
                                "label": "cellType",
                                "dataPath": "cellType"
                            },
                            {
                                "name": "level",
                                "type": "integer",
                                "label": "Level"
                            }
                        ]
                    }
                }
            }
        }
    ]
}