/**
 * @author Praveen Kumar Reddy Rachala
 * 
 * <DBConstants.java>, V.0.0.1
 *
 * Created on 04-March-2016 by Praveen R (Java Programmer)
 *
 * Copyright 2016 Ideabytes Ltd
 */
package org.com.ideabytes.resources.common;

public interface DBConstants {
	public final static String COMPANY_NAME = "companyName";
	public final static String GATEWAY_IP = "gatewayIp";
	public final static String UNCLASS_ID = "unclass_id";
	// DGVFF
	public final static String TABLE_ACTIVITY_LOG = "activity_log";
	public final static String SELECTBILLOFLADINGDETAILS = "SELECT * FROM `BillOfLadingDetails` WHERE ";
	public final static String SELECTIDFROMBILLOFLADINGDETAILS = "SELECT `Id` FROM `BillOfLadingDetails` WHERE ";
	public final static String SELECTIDTRASACTIONDATA = "SELECT `TransactionId` FROM `TransactionDetails` WHERE ";
	public final static String TRANSACTIONID = "TransactionId";
	public final static String PAGE = "Page";
	public final static String CONTAINERID = "ContainerId";
	public final static String BL = "BL";
	public final static String UNNUMBER = "UNNumber";
	public final static String PSN = "PSN";
	public final static String PKGGROUP = "PkgGroup";
	public final static String UNCLASS = "UNClass";
	public final static String PRIMARYPLACARD = "PrimaryPlacard";
	public final static String SUBSIDARYPLACARD = "SubsidaryPlacard";
	public final static String SUBSIDARYEXIST = "SubsidaryExist";
	public final static String GROUPNAME = "GroupName";
	public final static String DGMASS = "DgMass";
	public final static String DGGROSSMASS = "DgGrossMass";
	public final static String NUMBEROFUNITS = "NumberOfUnits";
	public final static String WEIGHTINDEX = "WeightIndex";
	public final static String ID = "Id";
	public final static String WEIGHTTYPE = "WeightType";
	public final static String UNTYPE = "UnType";
	public final static String SP = "SP";
	public final static String PKGTYPE = "PKGType";
	public final static String SGCODES = "SGCodes";
	public final static String SGGROUP = "SGGroup";
	public final static String FLASHPOINT = "FlashPoint";
	public final static String USERID = "UserId";
	public final static String TRANSACTIONSTATUS = "TransactionStatus";
	public final static String TABLECONFIG = "SELECT * FROM `Config` "; 
	public final static String VALUE = "value";
	public final static String DATA = "Data";
	public final static String SUBSTANCETYPE = "SubstanceType";
	public final static String SELECTSUBSTGROUPINFO = "SELECT * FROM `SubstGroupInfo`";
	public final static String MANIFESTID = "ManifestId";
	public final static String VERIFYSTATUS = "VerifyStatus";
	
	
	
	//Segregatoin constants
	public final static String GETTRANSACTIONDETAILS = "canada_transaction_details";
	public final static String TABLE_US_TRANSACTIONS = "us_transaction_details";
	public final static String TABLE_PRODUCT_DETAILS = "SELECT * FROM Product_Metadata";
	public final static String IDARRAY = "idArray";
	public final static String CONTAINERARRAY = "ContainerArray";
	
}
