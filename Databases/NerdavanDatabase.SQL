CREATE TABLE Quotation (
    DistributorID       Int                NOT NULL,
    ProductID           Int                NOT NULL,
    Price               Double(7, 2)       NULL,
    CONSTRAINT QuotationPK          PRIMARY KEY (DistributorID, ProductID),
    CONSTRAINT DistributorFK        FOREIGN KEY(DistributorID)
                        REFERENCES Distributor(DistributorID),
    CONSTRAINT ProductFK            FOREIGN KEY(ProductID)
                        REFERENCES Product(ProductID)
);