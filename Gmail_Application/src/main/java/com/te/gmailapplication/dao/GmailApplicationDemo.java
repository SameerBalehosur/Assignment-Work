package com.te.gmailapplication.dao;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.te.gmailapplication.dto.Account;
import com.te.gmailapplication.dto.Inbox;

public class GmailApplicationDemo {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("gmail");
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static String username;
	private static String pass;
	private static String email;

	public static void register(String username, String pass, String email) {
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		transaction.begin();
		Account account = new Account();
		account.setUser_name(username);
		account.setPassword(pass);
		account.setEmail(email);
		manager.persist(account);
		transaction.commit();

	}
	public static Account login(String email,String password) {
		manager=factory.createEntityManager();
		String getaccount="from Account where email=:email and password=:password";
		Query query=manager.createQuery(getaccount);
		query.setParameter("email", email);
		query.setParameter("password", password);
		Account account = (Account) query.getSingleResult();
		System.out.println("Hi "+account.getUser_name() +" welcome");
		return account;
	}
	public static void compose(String first_message, String second_message, Account account) {
		manager = factory.createEntityManager();
		transaction = manager.getTransaction();
		transaction.begin();
		Account account2= manager.find(Account.class, account.getUser_id());
		if(account2!=null) {
			manager.merge(account2);

			Inbox inbox=new Inbox();
			inbox.setMessege(first_message);
			inbox.setMessege_id(1);
			inbox.setAccount(account2);

			Inbox inbox2=new Inbox();
			inbox2.setMessege(second_message);
			inbox2.setMessege_id(2);
			inbox2.setAccount(account2);

			List<Inbox> listofmsg=new LinkedList<Inbox>();
			listofmsg.add(inbox);
			listofmsg.add(inbox2);

			account2.setListofMails(listofmsg);
			manager.persist(account2);
			System.out.println("All Mails/Messeges are saved");
			transaction.commit();
		}
	}
	public static List<Inbox> findallmails(Account account) {
		manager=factory.createEntityManager();
		int id=account.getUser_id();
		String fqry="from inbox where user_id=:userid";
		Query query = manager.createQuery(fqry);
		query.setParameter("userid", id);
		List<Inbox> resultList = query.getResultList();
		account.setListofMails(resultList);
		return resultList;

	}
}
