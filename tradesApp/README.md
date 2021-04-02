Problem Statement
There is a scenario where thousands of trades are flowing into one store, assume any way of transmission of trades. We need to create a one trade store, which stores the trade in the following order

Trade Id	Version	Counter-Party Id	Book-Id	Maturity Date	Created Date	Expired
T1	1	CP-1	B1	20/05/2020	<today date>	N
T2	2	CP-2	B1	20/05/2021	<today date>	N
T2	1	CP-1	B1	20/05/2021	14/03/2015	N
T3	3	CP-3	B2	20/05/2014	<today date>	Y

There are couples of validation, we need to provide in the above assignment
1.	During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
2.	Store should not allow the trade which has less maturity date then today date.
3.	Store should automatically update expire flag if in a store the trade crosses the maturity date.

Insert script-
insert into TRADES values('T1','B1','CP-1',SYSDATE,'N','2020-05-20',1);
insert into TRADES values('T2','B1','CP-2',SYSDATE,'N','2021-05-20',2);
insert into TRADES values('T2','B1','CP-1','2015-03-14','N','2021-05-20',1);
insert into TRADES values('T3','B2','CP-3',SYSDATE,'Y','2014-05-20',3);


Get service call--
http://localhost:8081/tradeApp
[{"tradeId":"T1","version":1,"counterParty":"CP-1","bookId":"B1","maturityDate":"2020-05-20","createdDate":"2021-04-02","expiredFlag":"Y"},
{"tradeId":"T2","version":2,"counterParty":"CP-2","bookId":"B1","maturityDate":"2021-05-20","createdDate":"2021-04-02","expiredFlag":"N"},
{"tradeId":"T3","version":3,"counterParty":"CP-3","bookId":"B2","maturityDate":"2014-05-20","createdDate":"2021-04-02","expiredFlag":"Y"}]