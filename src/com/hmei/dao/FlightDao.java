package com.hmei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.hmei.bean.BookedFlightInfo;
import com.hmei.bean.Booking;
import com.hmei.bean.Customer;
import com.hmei.bean.Flight;
import com.hmei.bean.FlightInfo;
import com.hmei.bean.FullInfo;
import com.hmei.bean.Manager;
import com.hmei.bean.SeatRemaining;
import com.hmei.bean.Ticket;
import com.hmei.util.DatabaseUtil;
import com.hmei.util.DateConverter;

public class FlightDao {
	private static Object object = new Object();

	public static boolean addFlight(Flight flight, Manager manager) {
		Connection conn = DatabaseUtil.getConnection();

		String addFlightSql = "insert into flight (flight_id, flight_name, departure_time, arrival_time, dep_airport_code"
				+ ", arr_airport_code, seat_A_amt, seat_B_amt, seat_C_amt) values (seq_flight.nextval, '"
				+ flight.getFlightName() + "', to_timestamp('" + flight.getDepTime() + "','YYYY-MM-DD HH24:MI'), "
				+ "to_timestamp('" + flight.getArrTime() + "','YYYY-MM-DD HH24:MI'), '" + flight.getDepAirportCode()
				+ "', '" + flight.getArrAirportCode() + "', " + flight.getSeatAAmt() + ", " + flight.getSeatBAmt()
				+ ", " + flight.getSeatCAmt() + ")";
		ResultSet rs = null;
		Statement addFlightStmt = null;


		try {
			addFlightStmt = conn.createStatement();
			rs = addFlightStmt.executeQuery(addFlightSql);
			if (rs.next() && initializeSeat(conn, flight,flight.getSeatAAmt()
					,flight.getSeatBAmt()
					,flight.getSeatCAmt())) {
				return true;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeStatement(addFlightStmt);
			DatabaseUtil.closeConnection(conn);
		}

		return false;
	}

	public static List<FlightInfo> searchFlight(String from, String to, String depDate) {
		List<FlightInfo> result = new ArrayList<>();
		String sql = "select * from(" + "select f.flight_id, f.flight_name, " + "a.airport_name as dep_airport,"
				+ "f.dep_airport_code," + "f.departure_time, a.city as dep_city, a.state as dep_state,"
				+ "a.country as dep_country," + "b.airport_name as arr_airport," + "f.arr_airport_code, f.arrival_time,"
				+ "b.city as arr_city, b.state as arr_state," + "b.country as arr_country "
				+ "from flight f join airport a " + "on dep_airport_code = airport_code " + "join airport b "
				+ "on arr_airport_code = b.airport_code)" + "where (dep_city= ? or dep_airport_code = ?) and "
				+ "(arr_city= ? or arr_airport_code = ?) and "
				+ "departure_time >= to_timestamp(?,'YYYY/MM/DD HH24:MI:SS.FF') "
				+ "and departure_time < to_timestamp(?,'YYYY/MM/DD HH24:MI:SS.FF') order by departure_time asc";
		Connection conn = DatabaseUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String fromPlace = from.toUpperCase();
			String toPlace = to.toUpperCase();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fromPlace);
			stmt.setString(2, fromPlace);
			stmt.setString(3, toPlace);
			stmt.setString(4, toPlace);
			stmt.setString(5, Timestamp.valueOf(DateConverter.stringToLocalDate(depDate).atStartOfDay()).toString());
			stmt.setString(6,
					Timestamp.valueOf(DateConverter.stringToLocalDate(depDate).plusYears(2).atStartOfDay()).toString());
			rs = stmt.executeQuery();
			while (rs.next()) {
				FlightInfo finfo = new FlightInfo();
				finfo.setFlightId(rs.getInt(1));
				finfo.setFlightName(rs.getString(2));
				finfo.setDepAirport(rs.getString(3));
				finfo.setDepAirportCode(rs.getString(4));

				finfo.setDepTime(rs.getTimestamp(5));
				finfo.setDepCity(rs.getString(6));
				finfo.setDepState(rs.getString(7));
				finfo.setDepCountry(rs.getString(8));

				finfo.setArrAirport(rs.getString(9));
				finfo.setArrAirportCode(rs.getString(10));

				finfo.setArrTime(rs.getTimestamp(11));
				finfo.setArrCity(rs.getString(12));
				finfo.setArrState(rs.getString(13));
				finfo.setArrCountry(rs.getString(14));
				result.add(finfo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeStatement(stmt);
			DatabaseUtil.closeConnection(conn);
		}

		return result;
	}

	public static boolean searchFlight(Flight flight) {
		String fName = flight.getFlightName();
		String fDepTime = flight.getDepTime();
		String fDepAirportCode = flight.getDepAirportCode();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = "select * from flight where flight_name = ? and departure_time = to_timestamp(?,'YYYY/MM/DD HH24:MI:SS.FF') and dep_airport_code = ?";
		System.out.println(fName + " " + fDepTime + " " + fDepAirportCode);
		conn = DatabaseUtil.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, fName);
			stmt.setString(2, fDepTime);
			stmt.setString(3, fDepAirportCode);
			rs = stmt.executeQuery();
			if (rs.next()) {
				flight.setFlightId(String.valueOf(rs.getInt("FLIGHT_ID")));
				flight.setArrAirportCode(rs.getString("ARR_AIRPORT_CODE"));
				flight.setArrTime(rs.getTimestamp("ARRIVAL_TIME").toString());
				flight.setSeatAAmt(rs.getInt("SEAT_A_AMT"));
				flight.setSeatBAmt(rs.getInt("SEAT_B_AMT"));
				flight.setSeatCAmt(rs.getInt("SEAT_C_AMT"));
				return true;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeStatement(stmt);
			DatabaseUtil.closeConnection(conn);
		}

		return false;
	}

	public static boolean updateFlight(Flight flight) {
		String sql = "update flight set flight_name = ?, departure_time = to_timestamp(?,'YYYY/MM/DD HH24:MI:SS.FF'),"
				+ "arrival_time = to_timestamp(?,'YYYY/MM/DD HH24:MI:SS.FF'), dep_airport_code = ?, arr_airport_code = ?"
				+ " where flight_id = ?";

		Connection conn = DatabaseUtil.getConnection();
		try {
			if (conn.getAutoCommit()) {
				try {
					conn.setAutoCommit(false);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, flight.getFlightName());
			stmt.setString(2, flight.getDepTime());
			stmt.setString(3, flight.getArrTime());
			stmt.setString(4, flight.getDepAirportCode());
			stmt.setString(5, flight.getArrAirportCode());
			stmt.setInt(6, Integer.valueOf(flight.getFlightId()));
			rs = stmt.executeQuery();
			if (rs.next()) {

				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeStatement(stmt);
			DatabaseUtil.closeConnection(conn);
		}

		return false;
	}

	public static boolean updateSeats(Connection connection, int flightId, int addSeatA, int addSeatB, int addSeatC) {
		String sql = "update remaining_seat set rem_seat_A = rem_seat_A + ?,"
				+ " rem_seat_B = rem_seat_B + ?, rem_seat_C = rem_seat_C + ? " + "where flight_id = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Connection conn = connection;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, addSeatA);
			stmt.setInt(2, addSeatB);
			stmt.setInt(3, addSeatC);
			stmt.setInt(4, flightId);
			rs = stmt.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally {
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeStatement(stmt);
			DatabaseUtil.closeConnection(conn);
		}

		return false;
	}

	public static boolean deleteFlight(int id) {
		String sql = "delete from flight where flight_id = ?";
		Connection conn = DatabaseUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeStatement(stmt);
			DatabaseUtil.closeConnection(conn);
		}

		return false;
	}

	public static boolean initializeSeat(Connection connection, Flight flight, int seatA, int seatB, int seatC) {
		String sql = "insert into remaining_seat values(?,?,?,?)";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection conn = connection;

		try {
			searchFlight(flight);
			stmt = conn.prepareStatement(sql);
			System.out.println(flight.getFlightId());
			stmt.setInt(1, Integer.valueOf(flight.getFlightId()));
			stmt.setInt(2, seatA);
			stmt.setInt(3, seatB);
			stmt.setInt(4, seatC);

			rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			else
			{
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally {
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeStatement(stmt);
			DatabaseUtil.closeConnection(conn);
		}


		return false;
	}

	public static SeatRemaining getSeatRem(int flightId) {
		Connection conn = DatabaseUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from remaining_seat where flight_id = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, flightId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				SeatRemaining seatRem = new SeatRemaining();
				seatRem.setRemSeatA(rs.getInt(2));
				System.out.println(rs.getInt(2));
				System.out.println(rs.getInt(3));
				System.out.println(rs.getInt(4));
				seatRem.setRemSeatB(rs.getInt(3));
				seatRem.setRemSeatC(rs.getInt(4));
				return seatRem;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeStatement(stmt);
			DatabaseUtil.closeConnection(conn);
		}


		return null;
	}

	public static boolean buyTickets(int flightId, int custId, String[] ticketApassengers, String[] ticketBpassengers,
			String[] ticketCpassengers) {
		synchronized (object) {
			Connection conn = DatabaseUtil.getConnection();
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			PreparedStatement stmt = null;
			ResultSet rs = null;
			boolean result = false;
			SeatRemaining seatRemInfo = getSeatRem(flightId);
			if (seatRemInfo.getRemSeatA() >= ticketApassengers.length
					&& seatRemInfo.getRemSeatB() >= ticketBpassengers.length
					&& seatRemInfo.getRemSeatC() >= ticketCpassengers.length) {
				String sql = "update remaining_seat set rem_seat_a = rem_seat_a - ?,"
						+ "rem_seat_b = rem_seat_b - ?, rem_seat_c = rem_seat_c - ? " + "where flight_id = ?";
				try {
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, ticketApassengers.length);
					stmt.setInt(2, ticketBpassengers.length);
					stmt.setInt(3, ticketCpassengers.length);
					stmt.setInt(4, flightId);
					rs = stmt.executeQuery();
					if (rs.next())
						result = generateTickets(conn, flightId, custId, ticketApassengers, ticketBpassengers, ticketCpassengers);
					if(result == false)
					{
						conn.rollback();
					}
					else
					{
						conn.commit();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						conn.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				finally {
					DatabaseUtil.closeResultSet(rs);
					DatabaseUtil.closeStatement(stmt);
					DatabaseUtil.closeConnection(conn);
				}

			}
			return result;
		}

	}

	private static boolean generateTickets(Connection connection, int flightId, int custId, String[] ticketApassengers,
			String[] ticketBpassengers, String[] ticketCpassengers) {
		String sql = "insert into ticket (flight_id, ticket_status, passenger_name, seat_class) values(?, 'Confirmed', ?, ?)";
		Connection conn = connection;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int index = 0;
		int ticketAamt = ticketApassengers.length;
		int ticketBamt = ticketBpassengers.length;
		int ticketCamt = ticketCpassengers.length;

		try {
			stmt = conn.prepareStatement(sql, new String[]{"ticket_id"});
			String timeStamp = Timestamp.valueOf(LocalDateTime.now()).toString();

			while (index < ticketAamt) {
				stmt.setInt(1, flightId);
				stmt.setString(2, ticketApassengers[index++]);
				stmt.setString(3, "A");

				stmt.execute();
				rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					int ticketId = rs.getInt(1);
					System.out.println(ticketId);
					if (!generateBookingRow(conn, ticketId, custId, timeStamp)) {
						return false;
					}
				}

			}
			index = 0;
			while (index < ticketBamt) {
				stmt.setInt(1, flightId);
				stmt.setString(2, ticketBpassengers[index++]);
				stmt.setString(3, "B");

				stmt.execute();
				rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					int ticketId = rs.getInt(1);
					if (!generateBookingRow(conn, ticketId, custId, timeStamp)) {
						return false;
					}
				}

			}
			index = 0;
			while (index < ticketCamt) {
				stmt.setInt(1, flightId);
				stmt.setString(2, ticketCpassengers[index++]);
				stmt.setString(3, "C");

				stmt.execute();
				rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					int ticketId = rs.getInt(1);
					if (!generateBookingRow(conn, ticketId, custId, timeStamp)) {
						return false;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return true;
	}

	private static boolean generateBookingRow(Connection connection, int ticketId, int custId, String timeStamp) {
		Connection conn = connection;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into booking values ( ?, ?, ?, ?, to_timestamp(?, 'YYYY-MM-DD HH24:MI:SS.FF'))";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ticketId);
			stmt.setInt(3, custId);
			stmt.setString(4, "Booked");
			String[] splittedTime = timeStamp.split("[-\\s:\\.]");
			String bookingTime = "";
			for (int i = 0; i < splittedTime.length; i++) {
				bookingTime += splittedTime[i];
			}
			stmt.setString(2, bookingTime);
			stmt.setString(5, timeStamp);
			rs = stmt.executeQuery();
			if (rs.next())
				return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	public static List<BookedFlightInfo> getBookedFlight(String custId)
	{
		Connection conn = DatabaseUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "select * from flight natural join ( " + 
				"select customer_id, booking_id, booking_status, booking_date," + 
				"ticket_id, t.flight_id, ticket_status, seat_class, passenger_name " + 
				"from booking b natural join ticket t " + 
				"where customer_id = ?) order by booking_date desc";
		
		List<BookedFlightInfo> bookedFlightInfos = new ArrayList<>();
		try 
		{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, Integer.valueOf(custId));
			rs = stmt.executeQuery();

			while(rs.next())
			{
				BookedFlightInfo bookedFlightInfo = new BookedFlightInfo();
				bookedFlightInfo.setCustId(custId);
				bookedFlightInfo.setBookingId(rs.getString("booking_id"));
				bookedFlightInfo.setTicketId(rs.getString("ticket_id"));
				bookedFlightInfo.setFlightId(rs.getString("flight_id"));
				
				bookedFlightInfo.setTicketStatus(rs.getString("ticket_status"));
				bookedFlightInfo.setSeatClass(rs.getString("seat_class"));
				bookedFlightInfo.setPassengerName(rs.getString("passenger_name"));
				bookedFlightInfo.setBookingStatus(rs.getString("booking_status"));
				bookedFlightInfo.setBookingDate(rs.getString("booking_date"));
				
				bookedFlightInfo.setFlightName(rs.getString("flight_name"));
				bookedFlightInfo.setDepTime(rs.getString("departure_time"));
				bookedFlightInfo.setDepAirportCode(rs.getString("dep_airport_code"));
				bookedFlightInfo.setArrTime(rs.getString("arrival_time"));
				bookedFlightInfo.setArrAirportCode(rs.getString("arr_airport_code"));
				
				bookedFlightInfos.add(bookedFlightInfo);
			}
			return bookedFlightInfos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeStatement(stmt);
			DatabaseUtil.closeConnection(conn);
		}
		return null;
	}
	
	public static boolean cancelTicket(BookedFlightInfo bfi)
	{
		int ticketId = Integer.valueOf(bfi.getTicketId());
		int flightId = Integer.valueOf(bfi.getFlightId());
		String sql = "update ticket set ticket_status = 'Cancelled' where ticket_id = ?";
		Connection conn = DatabaseUtil.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ticketId);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				String seatClass = bfi.getSeatClass();
				if(seatClass.equals("A"))
				{
					updateSeats(conn, flightId, 1, 0, 0);
				}
				else if(seatClass.equals("B"))
				{
					updateSeats(conn, flightId, 0, 1, 0);
				}
				else
				{
					updateSeats(conn, flightId, 0, 0, 1);
				}
				bfi.setTicketStatus("Cancelled");
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally {
			DatabaseUtil.closeResultSet(rs);
			DatabaseUtil.closeStatement(stmt);
			DatabaseUtil.closeConnection(conn);
		}
		
		return false;
	}
	
	public static List<FullInfo> getBookingList(Flight flight)
	{
		int flightId = Integer.valueOf(flight.getFlightId());
		Connection conn = DatabaseUtil.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * " + 
				"from customer natural join " + 
				"(select * " + 
				"from booking natural join " + 
				"(select * " + 
				"from ticket natural join " + 
				"(select flight_id, flight_name, departure_time, arrival_time, dep_airport_code, arr_airport_code " + 
				"from flight " + 
				"where flight_id = ?)))";
		List<FullInfo> list = new ArrayList<>();
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, flightId);
			rs = stmt.executeQuery();
			while(rs.next())
			{	
				FullInfo bfi = new FullInfo();
				Customer customer = bfi.getCustomer();
				Ticket ticket = bfi.getTicket();
				Booking booking = bfi.getBooking();
				bfi.setFlight(flight);
				
				flight.setArrTime(rs.getTimestamp("arrival_time").toString());
				flight.setArrAirportCode(rs.getString("arr_airport_code"));
				
				customer.setFirstName(rs.getString("firstname"));
				customer.setLastName(rs.getString("lastname"));
				customer.setGender(rs.getString("gender"));
				customer.setEmail(rs.getString("email"));

				ticket.setPassengerName(rs.getString("passenger_name"));
				ticket.setTicketStatus(rs.getString("ticket_status"));
				ticket.setSeatClass(rs.getString("seat_class"));
				
				booking.setBookingDate(rs.getTimestamp("booking_date").toString());
				booking.setBookingStatus(rs.getString("booking_Status"));
				
				
				
				list.add(bfi);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}


}
