package com.te.gmail_application.main;

import java.util.List;
import java.util.Scanner;

import com.te.gmailapplication.dao.GmailApplicationDemo;
import com.te.gmailapplication.dto.Account;
import com.te.gmailapplication.dto.Inbox;

public class GmailChecking {
	private static Scanner scanner = new Scanner(System.in);
	private static boolean exit;
	private static String username;
	private static String email;
	private static String password;

	public static void main(String[] args) {
		while (!exit) {
			System.out.println("Press 1 to login\nPress 2 to register");
			String string = scanner.next().toLowerCase();
			switch (string) {
			case "1":
				System.out.println("Enter your email: ");
				email = scanner.next();
				System.out.println("Enter password: ");
				password = scanner.next();
				Account account = GmailApplicationDemo.login(email, password);
				System.out.println();
				System.out.println();
				System.out.println("Press A to Compose\nPress B to show Inbox");
				String string2 = scanner.next().toUpperCase();
				switch (string2) {
				case "A":
					System.out.println("Enter first message: ");
					String msg = scanner.next();
					System.out.println("Enter second message: ");
					String msg1 = scanner.next();
					GmailApplicationDemo.compose(msg, msg1, account);
				case "B":
					List<Inbox> list = GmailApplicationDemo.findallmails(account);
					for (Inbox inbox : list) {
						System.out.println(inbox.getMessege());
					}
					System.out.println();
					break;
				default:
					System.out.println("Enter correctly!!");
					break;
				}
				// System.err.println("Do you want to continue: y/n");
				break;
			case "2":
				System.out.println("Enter username: ");
				username = scanner.next();
				System.out.println("Enter password: ");
				password = scanner.next();
				System.out.println("Enter email: ");
				email = scanner.next();
				GmailApplicationDemo.register(username, password, email);
				System.out.println("Registered successfully....");
				System.err.println("Do you want to continue: y/n");
				break;
			case "y":
				System.out.println("Press 1 to login\nPress 2 to register");
				break;
			case "n":
				System.err.println("Exited the application!!");
				exit = true;
				break;
			default:
				System.out.println("Enter correctly!!");
				break;
			}
		}
	}

}
