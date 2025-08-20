package com.mec.shopping.database

const val M_1_2_CREATE_NEW_TABLE = """
            CREATE TABLE shopping_item_new (
                item_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                event_id INTEGER NOT NULL, 
                item_name TEXT NOT NULL, 
                quantity REAL NOT NULL DEFAULT 1.0, 
                price REAL NOT NULL DEFAULT 0.0,
                FOREIGN KEY(event_id) REFERENCES shopping_event(id) ON DELETE CASCADE
            )
        """

const val M_1_2_COPY_DATA_TO_NEW_TABLE = """
            INSERT INTO shopping_item_new (item_id, event_id, item_name, quantity, price)
            SELECT item_id, event_id, item_name, quantity, price 
            FROM shopping_item
        """
