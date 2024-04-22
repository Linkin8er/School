CREATE TABLE Event (
    EventID          INTEGER        NOT NULL,
    EventName        CHAR(100)      NOT NULL,
    EventGame        CHAR(100)      NOT NULL,
    EventTime        DATE           NULL,
    EventDay         CHAR(100)      NULL,
    EventPrice       FLOAT(2)       NULL,
    EventDescription VARCHAR(500)   DEFAULT 'No Description' NULL,

    CONSTRAINT EventPK             PRIMARY KEY (EventID)
);

CREATE TABLE Customer (
   CustomerID 	      INTEGER	   NOT NULL,
   FirstName 	      CHAR(100)      NOT NULL,
   LastName 	      CHAR(100)      NOT NULL,
   PhoneNumber 	      INTEGER        NULL,
   Email             VARCHAR(100)	   NULL,
   
   CONSTRAINT CustomerPK            PRIMARY KEY (CustomerID)
);

CREATE TABLE Distributor (
DistributorID     INTEGER        NOT NULL,
  	DistributorName   CHAR(100)      NOT NULL,
  	PhoneNumber 	      INTEGER        NULL,
   Email 	            CHAR(100)      NULL,
   WarehouseLocation CHAR(100)      NOT NULL,

   CONSTRAINT DistributorPK   	PRIMARY KEY (DistributorID)
);

CREATE TABLE Product (
   ProductID 	      INTEGER        NOT NULL,
   ProductName 	      CHAR(100)      NOT NULL,
   ProductPrice      FLOAT(2)       NOT NULL,
   Manufacturer      CHAR(100)      NULL,

   CONSTRAINT ProductPK             PRIMARY KEY(ProductID)
);

CREATE TABLE EventParticipation (
   CustomerID 	      INTEGER        NOT NULL,
   EventID           INTEGER       NOT NULL,

   CONSTRAINT EventParticipationPK PRIMARY KEY (CustomerID, EventID),
   CONSTRAINT EventCustomerIDFK    FOREIGN KEY (CustomerID) 
              REFERENCES Customer(CustomerID),
   CONSTRAINT EventIDFK            FOREIGN KEY (EventID) 
              REFERENCES Event(EventID)
);

CREATE TABLE Orders (
   OrderID           INTEGER       NOT NULL,
   CustomerID        INTEGER       NOT NULL,
   OrderDate         DATE          NOT NULL,
   SubTotal          FLOAT(2)      NOT NULL,
   Tax               FLOAT(2)      NOT NULL,
   Total             FLOAT(2)      NOT NULL,

   CONSTRAINT OrdersPK             PRIMARY KEY (OrderID),
   CONSTRAINT OrderCustomerIDFK    FOREIGN KEY (CustomerID) 
              REFERENCES Customer(CustomerID)
);

CREATE TABLE OrderItems (
   OrderID           INTEGER       NOT NULL,
   ProductID         INTEGER       NOT NULL,
   Quantity          INTEGER       NOT NULL,
   Price             FLOAT(2)      NOT NULL,
   ExtendedPrice     FLOAT(2)      NOT NULL,

   CONSTRAINT OrderItemsPK         PRIMARY KEY (OrderID, ProductID),
   CONSTRAINT OrderItemsOrderFK    FOREIGN KEY (OrderID) 
              REFERENCES Orders(OrderID),
   CONSTRAINT OrderItems_ProductFK FOREIGN KEY (ProductID) 
              REFERENCES Product(ProductID)
);

CREATE TABLE Quotation (
   DistributorID     INTEGER       NOT NULL,
   ProductID         INTEGER       NOT NULL,
   Price             FLOAT(2)      NULL,

   CONSTRAINT QuotationPK          PRIMARY KEY (DistributorID, ProductID),
   CONSTRAINT DistributorFK        FOREIGN KEY(DistributorID)
              REFERENCES Distributor(DistributorID),
   CONSTRAINT ProductFK            FOREIGN KEY(ProductID)
              REFERENCES Product(ProductID)
);
