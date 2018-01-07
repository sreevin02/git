/**
 * Ideabytes Software India Pvt Ltd.                                
 * 
 * 50 Jayabheri Enclave, Gachibowli, HYD           
 *
 * @author Praveen Kumar Reddy Rachala
 * 
 * <DatabaseConf.java>, V.0.0.1
 *
 * Created on 04-March-2016 by Praveen R (Java Programmer)
 *
 * Copyright 2016 Ideabytes Ltd
 */

package org.com.ideabytes.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.com.ideabytes.exceptions.DGVFFNestableException;
import org.com.ideabytes.resources.common.DBConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DatabaseConf implements DBConstants {
	public static final Log log = LogFactory.getLog(DatabaseConf.class);

	/**
	 * @author praveen kumar reddy
	 * 
	 * @Created on 04-March-2016
	 * 
	 * @modified by R praveen Kumar Reddy on 13-03-2016
	 * 
	 * @description this method Update all exception and Success messages in
	 *              activity log table
	 * 
	 * @param String
	 *            module
	 * 
	 * @param String
	 *            action
	 * 
	 * @param String
	 *            userId
	 * 
	 * @param String
	 *            data
	 * 
	 * @param String
	 *            status
	 * 
	 */
	public void updateActivityLog(String module, String action, int userId,
			String data, String status) {
		Statement statement = null;
		Connection c = null;
		try {
			c = DatabaseConnection.getConnection();
			statement = c.createStatement();
			Date today = new java.util.Date();
			Timestamp now = new java.sql.Timestamp(today.getTime());
			String insertIntoActivityLog = "insert into "
					+ TABLE_ACTIVITY_LOG
					+ " (module,action,user_id,data,status,created_datetime) VALUES("
					+ "\"" + module + "\"" + "," + "\"" + action + "\"" + ","
					+ userId + "," + "\'" + data + "" + "'," + "\"" + status
					+ "\"" + ",'" + now + "')";
			if (log.isDebugEnabled()) {
				log.debug("insertIntoActivityLog" + insertIntoActivityLog);
			}
			
			statement.executeUpdate(insertIntoActivityLog);
		} catch (SQLException se) {
			se.printStackTrace();
			log.error("SQL exception caught while updateActivityLog"
					+ se.getMessage());
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_INTERNAL_SERVER_ERROR,
					"SQL exception caught while updateActivityLog");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception caught while executing updateActivityLog query");
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_AUTHENTICATION_ERROR,
					"Exception caught while executing updateActivityLog query");
		} finally {
			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (c != null) {
					c.close();
					c = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception caught while closing the connection"
						+ " message: " + e.getMessage());
				throw new DGVFFNestableException(
						DGVFFNestableException.CODE_INTERNAL_SERVER_ERROR,
						"Exception caught while closing the connection");
			}
		}
	}

	/**
	 * this function return JSONArray data
	 * 
	 * @author praveen kumar reddy
	 * 
	 * @Created on 04-March-2016
	 * 
	 * @modified by R praveen Kumar Reddy on 13-03-2016
	 * 
	 * @description this method user Data into BilladingDeatils table
	 * 
	 * @param String
	 *            pass which one wants latest Id like product Id or billoflading
	 * 
	 * @return String -> get the latest Ids
	 */
	public String insertBillOfLadingDetails(String reqText) {
		Statement statement = null;
		Statement inserTrnsStmt = null;
		Statement selectTransId = null;
		Connection c = null;
		ResultSet rs = null;
		ResultSet rsSelectTransId = null;
		String id = "";
		try {
			JSONObject inputfromUser = new JSONObject(reqText);
			String trasactionId = inputfromUser.getString(TRANSACTIONID);
			String bL = inputfromUser.getString(BL);
			String containerId = inputfromUser.getString(CONTAINERID);
			int page = Integer.parseInt(inputfromUser.getString(PAGE));
			String userId = inputfromUser.getString(USERID);
			String insertBLDetails = "INSERT INTO `BillOfLadingDetails`(`TransactionId`, "
					+ "`ContainerId`, `BL`, `UNNumber`, `PSN`, `PkgGroup`, `UNClass`, `PrimaryPlacard`, `SubsidaryPlacard`, "
					+ "`SubsidaryExist`, `GroupName`, `DgMass`, `DgGrossMass`, `NumberOfUnits`, `WeightIndex`, `WeightType`, "
					+ "`UnType`,`SP`,`PKGType`, `SGCodes`, `SGGroup`, `FlashPoint`, `UserId` "
					+ ") VALUES (" + "'"
					+ trasactionId
					+ "'"
					+ ", "
					+ "'"
					+ containerId
					+ "',"
					+ " '"
					+ bL
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(UNNUMBER)
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(PSN)
					+ "'"
					+ ","
					+ " '"
					+ inputfromUser.getString(PKGGROUP)
					+ "'"
					+ ","
					+ " '"
					+ inputfromUser.getString(UNCLASS)
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(PRIMARYPLACARD)
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(SUBSIDARYPLACARD)
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(SUBSIDARYEXIST)
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(GROUPNAME)
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(DGMASS)
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(DGGROSSMASS)
					+ "'"
					+ ", "
					+ "'"
					+ Integer.parseInt(inputfromUser.getString(NUMBEROFUNITS))
					+ "'"
					+ ", "
					+ "'"
					+ Double.parseDouble(inputfromUser.getString(WEIGHTINDEX))
					+ "'"
					+ ", "
					+ "'"
					+ Integer.parseInt(inputfromUser.getString(WEIGHTTYPE))
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(UNTYPE)
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(SP)
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(PKGTYPE)
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(SGCODES)
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(SGGROUP)
					+ "'"
					+ ", "
					+ "'"
					+ inputfromUser.getString(FLASHPOINT)
					+ "'"
					+ ", "
					+ "'"
					+ userId + "')";

			if (log.isDebugEnabled()) {
				log.debug("*****insertIntoBillofladingDetailsInformation****"
						+ insertBLDetails);
			}

			c = DatabaseConnection.getConnection();
			statement = c.createStatement();
			synchronized (insertBLDetails) {
				statement.executeUpdate(insertBLDetails,
						Statement.RETURN_GENERATED_KEYS);
				rs = statement.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getString(1);
				}
			}
			selectTransId = c.createStatement();
			String selectTrasactionData = SELECTIDTRASACTIONDATA
					+ "`TransactionId` = '" + trasactionId + "'";
			if (log.isDebugEnabled()) {
				log.debug("*****selectTrasactionData****"
						+ selectTrasactionData);
			}
			rsSelectTransId = selectTransId.executeQuery(selectTrasactionData);

			if (!rsSelectTransId.next()) {
				inserTrnsStmt = c.createStatement();
				String insertTrasactionQuery = "INSERT INTO `TransactionDetails`(`TransactionId`, `Page`, `UserID`) VALUES ("
						+ "'"
						+ trasactionId
						+ "','"
						+ page
						+ "','"
						+ userId
						+ "')";
				if (log.isDebugEnabled()) {
					log.debug("*****insertTrasactionQuery****"
							+ insertTrasactionQuery);
				}
				inserTrnsStmt.executeUpdate(insertTrasactionQuery);
			} else {
				inserTrnsStmt = c.createStatement();
				String updateDateTransQuery = " UPDATE `TransactionDetails` SET `Date`= NOW()  WHERE `TransactionId` = '"
						+ trasactionId + "'";
				if (log.isDebugEnabled()) {
					log.debug("*****updateDateTransQuery****"
							+ updateDateTransQuery);
				}
				inserTrnsStmt.executeUpdate(updateDateTransQuery);

			}
		}

		catch (SQLException se) {
			se.printStackTrace();
			log.error("SQL exception caught while insertBillOfLadingDetails: "
					+ se.getMessage());
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_INTERNAL_SERVER_ERROR,
					"SQL exception caught while insertBillOfLadingDetails");
		} catch (JSONException je) {
			je.printStackTrace();
			log.error("JSON exception caught while insertBillOfLadingDetails");
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_AUTHENTICATION_ERROR,
					"JSON exception caught while insertBillOfLadingDetails");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (rsSelectTransId != null) {
					rsSelectTransId.close();
					rsSelectTransId = null;
				}
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (selectTransId != null) {
					selectTransId.close();
					selectTransId = null;
				}
				if (inserTrnsStmt != null) {
					inserTrnsStmt.close();
					inserTrnsStmt = null;
				}
				if (c != null) {
					c.close();
					c = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception caught while closing the connection"
						+ " message: " + e.getMessage());
				throw new DGVFFNestableException(
						DGVFFNestableException.CODE_INTERNAL_SERVER_ERROR,
						"Exception caught while closing the connection");
			}
		}
		return id;

	}

	/**
	 * this function return JSONArray data
	 * 
	 * @author praveen kumar reddy
	 * 
	 * @Created on 10-03-2016
	 * 
	 * @modified by R praveen Kumar Reddy on 13-03-2016
	 * 
	 * @description @description this method user data(Either Transaction Id or
	 *              Billof ading or Container Id or three)Deleted from
	 *              BilladingDeatils table
	 * 
	 *              "UserId" is Mandatory
	 * 
	 * @param String
	 *            pass which one wants latest Id like product Id or billoflading
	 * 
	 * @return String -> get the latest Ids
	 */
	public JSONArray GetBillOfLadingDetails(String reqText) {
		ResultSet rs = null;
		Statement statement = null;
		Connection c = null;
		JSONArray jArray;
		try {
			JSONObject inputfromUser = new JSONObject(reqText);
			String TransactionId = inputfromUser.getString(TRANSACTIONID);
			String ContainerId = inputfromUser.getString(CONTAINERID);
			String bl = inputfromUser.getString(BL);
			String UserId = inputfromUser.getString(USERID);
			String queryType = "";
			if (!TransactionId.equalsIgnoreCase("")
					&& !ContainerId.equalsIgnoreCase("")
					&& !bl.equalsIgnoreCase("")) {
				queryType = "`TransactionId` = '" + TransactionId
						+ "' and `ContainerId` = '" + ContainerId
						+ "' and `BL` ='" + bl + "'";
			} else if (!TransactionId.equalsIgnoreCase("")
					&& !ContainerId.equalsIgnoreCase("")) {
				queryType = "`TransactionId` = '" + TransactionId
						+ "' and `ContainerId` = '" + ContainerId + "'";
			} else if (!ContainerId.equalsIgnoreCase("")
					&& !bl.equalsIgnoreCase("")) {
				queryType = "`ContainerId` = '" + ContainerId + "' and `BL` ='"
						+ bl + "'";
			} else if (!TransactionId.equalsIgnoreCase("")
					&& !bl.equalsIgnoreCase("")) {
				queryType = "`TransactionId` = '" + TransactionId
						+ "'and `BL` ='" + bl + "'";
			} else if (!TransactionId.equalsIgnoreCase("")) {
				queryType = "`TransactionId` = '" + TransactionId + "'";
			} else if (!ContainerId.equalsIgnoreCase("")) {
				queryType = "`ContainerId` = '" + ContainerId + "'";
			} else if (!bl.equalsIgnoreCase("")) {
				queryType = "`BL` ='" + bl + "'";
			}

			String selectBLDetails = SELECTBILLOFLADINGDETAILS + queryType
					+ " and `UserId` = '" + UserId + "'";
			c = DatabaseConnection.getConnection();
			statement = c.createStatement();
			if (log.isDebugEnabled()) {
				log.debug("selectBLDetails" + selectBLDetails);
			}
			rs = statement.executeQuery(selectBLDetails);
			jArray = new JSONArray();
			while (rs.next()) {
				JSONObject jo = new JSONObject();
				jo.put(TRANSACTIONID, rs.getString(TRANSACTIONID));
				jo.put(CONTAINERID, rs.getString(CONTAINERID));
				jo.put(BL, rs.getString(BL));
				jo.put(UNNUMBER, rs.getString(UNNUMBER));
				jo.put(PSN, rs.getString(PSN));
				jo.put(PKGGROUP, rs.getString(PKGGROUP));
				jo.put(UNCLASS, rs.getString(UNCLASS));
				jo.put(PRIMARYPLACARD, rs.getString(PRIMARYPLACARD));
				jo.put(SUBSIDARYPLACARD, rs.getString(SUBSIDARYPLACARD));
				jo.put(SUBSIDARYEXIST, rs.getString(SUBSIDARYEXIST));
				jo.put(GROUPNAME, rs.getString(GROUPNAME));
				jo.put(DGMASS, rs.getString(DGMASS));
				jo.put(DGGROSSMASS, rs.getString(DGGROSSMASS));
				jo.put(NUMBEROFUNITS, rs.getString(NUMBEROFUNITS));
				jo.put(WEIGHTINDEX, rs.getString(WEIGHTINDEX));
				jo.put(WEIGHTTYPE, rs.getString(WEIGHTTYPE));
				jo.put(UNTYPE, rs.getString(UNTYPE));
				jo.put(SP, rs.getString(SP));
				jo.put(PKGTYPE, rs.getString(PKGTYPE));
				jo.put(SGCODES, rs.getString(SGCODES));
				jo.put(SGGROUP, rs.getString(SGGROUP));
				jo.put(FLASHPOINT, rs.getString(FLASHPOINT));
				jo.put(USERID, rs.getString(USERID));
				jArray.put(jo);
			}
		} catch (SQLException se) {
			se.printStackTrace();
			log.error("SQL exception caught while GetBillOfladdingDetails: "
					+ se.getMessage());
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_INTERNAL_SERVER_ERROR,
					"SQL exception caught while GetBillOfladdingDetails");
		} catch (JSONException je) {
			je.printStackTrace();
			log.error("JSON exception caught while GetBillOfladdingDetails");
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_AUTHENTICATION_ERROR,
					"JSON exception caught while GetBillOfladdingDetails");
		} finally {
			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (c != null) {
					c.close();
					c = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception caught while closing the connection"
						+ " message: " + e.getMessage());
				throw new DGVFFNestableException(
						DGVFFNestableException.CODE_INTERNAL_SERVER_ERROR,
						"Exception caught while closing the connection");
			}
		}
		return JsonCoverter(jArray);

	}

	/**
	 * this function return JSONArray
	 * 
	 * @author R Praveen Kumar Reddy
	 * 
	 * @createdBy R Praveen Kumar Reddy
	 * 
	 * @modified by R Praveen Kumar Reddy on 13-03-2016
	 * 
	 * @Decription this method Grouping all Billoflading Data and Based On
	 *             container Id and all Duplicates
	 * 
	 * @param JSONArray
	 * 
	 * @return JSONArray ->
	 */
	public JSONArray JsonCoverter(JSONArray jdata) {
		Set<String> AllContainerIds = new HashSet<String>();
		JSONArray outerArray = null;
		JSONArray dataArray = null;
		try {
			// here get all Container Ids and remove all duplicates
			for (int i = 0; i < jdata.length(); i++) {
				AllContainerIds.add(jdata.getJSONObject(i).getString(
						CONTAINERID));
			}
			List<String> convertList = new ArrayList<String>(AllContainerIds);
			Collections.sort(convertList); 
			outerArray = new JSONArray();

			// here insert all Undata based On Container Ids

			for (int k = 0; k < convertList.size(); k++) {
				JSONObject InnerJdata = new JSONObject();
				InnerJdata.put(CONTAINERID, convertList.get(k));
				dataArray = new JSONArray();
				for (int j = 0; j < jdata.length(); j++) {
					if ((convertList).get(k).equalsIgnoreCase(
							jdata.getJSONObject(j).getString(CONTAINERID))) {
						dataArray.put(jdata.getJSONObject(j));
					}
				}
				InnerJdata.put(DATA, dataArray);
				outerArray.put(InnerJdata);

			}

		} catch (JSONException je) {
			je.printStackTrace();
			log.error("JSON exception caught while GetBillOfladdingDetails");
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_AUTHENTICATION_ERROR,
					"JSON exception caught while GetBillOfladdingDetails");
		}
		return outerArray;
	}

	/**
	 * this function return status not equal Zero then successfully Deleted
	 * 
	 * @author praveen kumar reddy
	 * 
	 * @Created on 10-03-2016
	 * 
	 * @modified by R praveen Kumar Reddy on 13-03-2016
	 * 
	 * @description this method user data(Either Transaction Id or Billof ading
	 *              or Container Id or three)Deleted from BilladingDeatils table
	 * 
	 *              "Id" @param is Mandatory
	 * 
	 * @param String
	 *            pass which one wants latest Id like product Id or billoflading
	 * 
	 * @return String -> get the latest Ids
	 */
	public int deleteBillofladingDetails(String reqText) {
		ResultSet rs = null;
		Statement statement = null;
		Statement Updatestatement = null;
		Statement UpdateTransDate = null;
		Connection c = null;
		int Status = 0;
		try {
			JSONObject inputfromUser = new JSONObject(reqText);
			String TransactionId = inputfromUser.getString(TRANSACTIONID);
			String ContainerId = inputfromUser.getString(CONTAINERID);
			String bl = inputfromUser.getString(BL);
			String Id = inputfromUser.getString(ID);
			String queryType = "";
			if (!TransactionId.equalsIgnoreCase("")
					&& !ContainerId.equalsIgnoreCase("")
					&& !bl.equalsIgnoreCase("")) {
				queryType = "`TransactionId` = '" + TransactionId
						+ "' and `ContainerId` = '" + ContainerId
						+ "' and `BL` ='" + bl + "'";
			} else if (!TransactionId.equalsIgnoreCase("")
					&& !ContainerId.equalsIgnoreCase("")) {
				queryType = "`TransactionId` = '" + TransactionId
						+ "' and `ContainerId` = '" + ContainerId + "'";
			} else if (!ContainerId.equalsIgnoreCase("")
					&& !bl.equalsIgnoreCase("")) {
				queryType = "`ContainerId` = '" + ContainerId + "' and `BL` ='"
						+ bl + "'";
			} else if (!TransactionId.equalsIgnoreCase("")
					&& !bl.equalsIgnoreCase("")) {
				queryType = "`TransactionId` = '" + TransactionId
						+ "'and `BL` ='" + bl + "'";
			} else if (!TransactionId.equalsIgnoreCase("")) {
				queryType = "`TransactionId` = '" + TransactionId + "'";
			} else if (!ContainerId.equalsIgnoreCase("")) {
				queryType = "`ContainerId` = '" + ContainerId + "'";
			} else if (!bl.equalsIgnoreCase("")) {
				queryType = "`BL` ='" + bl + "'";
			}

			String selectBLDetails = SELECTIDFROMBILLOFLADINGDETAILS
					+ queryType + " and `Id` = '" + Id + "'";
			c = DatabaseConnection.getConnection();
			statement = c.createStatement();
			if (log.isDebugEnabled()) {
				log.debug("selectBLDetails" + selectBLDetails);
			}
			rs = statement.executeQuery(selectBLDetails);
			if (rs.next()) {
				Updatestatement = c.createStatement();
				String id = String.valueOf(rs.getInt(ID));
				String updateQuery = "DELETE FROM `BillOfLadingDetails` WHERE `Id`= '"
						+ id + "'";
				Status = Updatestatement.executeUpdate(updateQuery);
				if (!TransactionId.equalsIgnoreCase("")) {
					String updateDateTransQuery = " UPDATE `TransactionDetails` SET `Date`= NOW()  WHERE `TransactionId` = '"
							+ TransactionId + "'";
					if (log.isDebugEnabled()) {
						log.debug("*****updateDateTransQuery****"
								+ updateDateTransQuery);
					}
					UpdateTransDate = c.createStatement();
					UpdateTransDate.executeUpdate(updateDateTransQuery);
				}

			}
		} catch (SQLException se) {
			se.printStackTrace();
			log.error("SQL exception caught while GetBillOfladdingDetails: "
					+ se.getMessage());
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_INTERNAL_SERVER_ERROR,
					"SQL exception caught while GetBillOfladdingDetails");
		} catch (JSONException je) {
			je.printStackTrace();
			log.error("JSON exception caught while GetBillOfladdingDetails");
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_AUTHENTICATION_ERROR,
					"JSON exception caught while GetBillOfladdingDetails");
		} finally {
			try {
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (Updatestatement != null) {
					Updatestatement.close();
					Updatestatement = null;
				}
				if (UpdateTransDate != null) {
					UpdateTransDate.close();
					UpdateTransDate = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (c != null) {
					c.close();
					c = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception caught while closing the connection"
						+ " message: " + e.getMessage());
				throw new DGVFFNestableException(
						DGVFFNestableException.CODE_INTERNAL_SERVER_ERROR,
						"Exception caught while closing the connection");
			}
		}
		return Status;

	}

	/**
	 * this function return get Latest Id
	 * 
	 * @author praveen kumar reddy
	 * 
	 * @Created on 10-03-2016
	 * 
	 * @modified by R praveen Kumar Reddy on 13-03-2016
	 * 
	 * @description get the getLatest Id from Config Information based on Type
	 *              of variable
	 * 
	 * @param String
	 *            pass which one wants latest Id like product Id or billoflading
	 * 
	 * @return String -> get the latest Ids
	 */
	public String getLatestId(String type) {
		ResultSet rs = null;
		Statement statement = null;
		Statement statement1 = null;
		Connection c = null;
		try {
			c = DatabaseConnection.getConnection();
			statement = c.createStatement();
			statement1 = c.createStatement();
			String selectTableSQL = TABLECONFIG + "WHERE `Key` ='" + type + "'";
			if (log.isDebugEnabled()) {
				log.debug("customer information " + selectTableSQL);
			}
			rs = statement.executeQuery(selectTableSQL);
			int value = 0;
			if (rs.next()) {
				value = Integer.parseInt(rs.getString(VALUE)) + 1;

				String updateSQL = "UPDATE `Config` SET `Value`='" + value
						+ "' WHERE `Key` ='" + type + "'";
				if (log.isDebugEnabled()) {
					log.debug("update config information " + updateSQL);
				}
				statement1.executeUpdate(updateSQL);
			}

			return String.valueOf(value);
		} catch (SQLException se) {
			se.printStackTrace();
			log.error("SQL exception caught while getLatestId"
					+ se.getMessage());
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_INTERNAL_SERVER_ERROR,
					"SQL exception caught while getLatestId");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception caught while executing getLatestId query");
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_AUTHENTICATION_ERROR,
					"Exception caught while executing getLatestId query");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (statement1 != null) {
					statement1.close();
					statement1 = null;
				}
				if (c != null) {
					c.close();
					c = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception caught while closing the connection"
						+ " message: " + e.getMessage());
				throw new DGVFFNestableException(
						DGVFFNestableException.CODE_INTERNAL_SERVER_ERROR,
						"Exception caught while closing the connection");
			}
		}
	}
	/**
	 * this function return Substance GroupInfo
	 * 
	 * @author praveen kumar Reddy
	 * 
	 * @created By Praveen Kumar Reddy on 10-03-2016
	 * 
	 * @modified by R praveen Kumar Reddy on 13-03-2016
	 * 
	 * @Description get the getSubstance GroupInfo based on Type of variable
	 *              type of
	 * 
	 *              JsonObject
	 * 
	 * @param String
	 *            reqText
	 * 
	 * @return String -> JSONArray
	 */
	public JSONArray getSubstGroupInfo(String reqText) {
		ResultSet rs = null;
		Statement statement = null;
		Connection c = null;
		JSONArray jArray = new JSONArray();
		try {

			String query = "";
			String param = "";
			c = DatabaseConnection.getConnection();
			statement = c.createStatement();
			JSONObject inputfromUser = new JSONObject(reqText);
			JSONArray Data = inputfromUser.getJSONArray(DATA);
			for (int i = 0; i < Data.length(); i++) {
				if (i == 0) {
					param = "'" + Data.getString(i) + "'";
				} else {
					param = param + ",'" + Data.getString(i) + "'";
				}
			}
			if (param.contains("0")) {
				query = SELECTSUBSTGROUPINFO;
			} else {
				query = SELECTSUBSTGROUPINFO + " WHERE `Id` in(" + param + ")";
			}
			if (log.isDebugEnabled()) {
				log.debug("SubstGroupInfo query" + query);
			}
			rs = statement.executeQuery(query);

			while (rs.next()) {
				JSONObject jobject = new JSONObject();
				jobject.put(ID, rs.getString(ID));
				jobject.put(SUBSTANCETYPE, rs.getString(SUBSTANCETYPE));
				jArray.put(jobject);
			}
		} catch (SQLException se) {
			se.printStackTrace();
			log.error("SQL exception caught while getSubstGroupInfo: "
					+ se.getMessage());
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_INTERNAL_SERVER_ERROR,
					"SQL exception caught while getSubstGroupInfo");
		} catch (JSONException je) {
			je.printStackTrace();
			log.error("JSON exception caught while getSubstGroupInfo");
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_AUTHENTICATION_ERROR,
					"JSON exception caught while getSubstGroupInfo");
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (statement != null) {
					statement.close();
					statement = null;
				}
				if (c != null) {
					c.close();
					c = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Exception caught while closing the connection"
						+ " message: " + e.getMessage());
				throw new DGVFFNestableException(
						DGVFFNestableException.CODE_INTERNAL_SERVER_ERROR,
						"Exception caught while closing the connection");
			}
		}
		return jArray;
	}

}
