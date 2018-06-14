package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;

import java.util.Calendar;
import java.util.Date;

import model.*;

public class Main {

	private static final Logger log = Logger.getLogger(Main.class);

	private static EntityManagerFactory sessionFactory;
	@PersistenceContext
	private static EntityManager entitymanager;

	static SimpleDateFormat dateForm = new SimpleDateFormat("dd.MM.yyyy");
	static SimpleDateFormat timeForm = new SimpleDateFormat("dd.MM.yyyy mm:hh");

	private Main() {
		super();
	}

	public static void main(String[] args) {

		BasicConfigurator.configure();

		try {
			log.info("Starting \"Mapping Perstistent Classes and Associations\" (task1)");
			sessionFactory = Persistence.createEntityManagerFactory("westbahn");
			entitymanager = sessionFactory.createEntityManager();
			fillDB(entitymanager);
			task01();
			log.info("Starting \"Working with JPA-QL and the Hibernate Criteria API\" (task2)");
			log.setLevel(Level.OFF);
			task02();
			task02a();
			task02b();
			task02c();
			log.setLevel(Level.ALL);
			task03(entitymanager);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entitymanager.getTransaction().isActive())
				entitymanager.getTransaction().rollback();
			entitymanager.close();
			sessionFactory.close();
		}
	}

	public static void fillDB(EntityManager em) throws ParseException {
		em.getTransaction().begin();
		List<Bahnhof> bahnhoefe = new ArrayList<Bahnhof>();
		List<Strecke> strecken = new ArrayList<Strecke>();
		List<Zug> zuege = new ArrayList<Zug>();


		Bahnhof wienhbf = new Bahnhof("WienHbf", 0, 0, 0, true);
		Bahnhof shbf = new Bahnhof("SalzburgHbf", 20, 60, 120, true);
		Bahnhof amstetten = new Bahnhof("Amstetten", 40, 124, 169, false);
		Bahnhof linz = new Bahnhof("Linz-Ost", 140, 320, 250, false);
		Bahnhof huetteldorf = new Bahnhof("Huetteldorf", 3, 5, 19, false);
		Bahnhof wels = new Bahnhof("Wels-Zentrum", 102, 400, 250, true);
		bahnhoefe.add(wienhbf);
		bahnhoefe.add(shbf);
		bahnhoefe.add(amstetten);
		bahnhoefe.add(linz);
		bahnhoefe.add(huetteldorf);
		bahnhoefe.add(wels);


		Strecke wien_salzburg = new Strecke(wienhbf, amstetten, shbf);
		Strecke wien_linz = new Strecke(wienhbf, amstetten, linz);
		strecken.add(wien_salzburg);
		strecken.add(wien_linz);


		Zug wiener_linien = new Zug(at_time(5, 20), 450, 20, 10, wienhbf, shbf );
		Zug rex = new Zug(at_time(8, 8), 100, 5, 0, linz, wels );
		zuege.add(wiener_linien);
		zuege.add(rex);

		Zahlung maestro = new Maestro();


		Ticket wochenkarte = new Zeitkarte(ZeitkartenTyp.WOCHENKARTE, new Date(), wien_linz, maestro);

		Benutzer armin = new Benutzer("Armin", "Freudenthaler", "test@mail.at", "TestPW", "147", (long) 300, wochenkarte);

		Reservierung r = new Reservierung(get_tomorrow(), 10, 100, StatusInfo.ONTIME, wiener_linien, wien_linz, armin, maestro);

		for (Bahnhof b : bahnhoefe)
			em.persist(b);

		for (Strecke s: strecken)
			em.persist(s);

		for (Zug z: zuege)
			em.persist(z);

		em.persist(wochenkarte);
		em.persist(armin);
		em.persist(r);
		em.flush();
		em.getTransaction().commit();
	}

	public static void task01() throws ParseException, InterruptedException {
	}

	public static <T> void task02() throws ParseException {
		Query q = entitymanager.createNamedQuery("Bahnhof.getAll");

		List<?> l = q.getResultList();

		for (Object b : l) {
			Bahnhof bhf = null;
			if (b instanceof Bahnhof) {
				bhf = (Bahnhof) b;
				System.out.println("Bahnhof: " + bhf.getName());
			}
		}
	}

	public static void task02a() throws ParseException {
	}

	public static void task02b() throws ParseException {
	}

	public static void task02c() throws ParseException {
	}

	public static void task03(EntityManager em) {
	}

	public static void validate(Object obj) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> violations = validator.validate(obj);
		for (ConstraintViolation<Object> violation : violations) {
			log.error(violation.getMessage());
			System.out.println(violation.getMessage());
		}
	}

	public static Date at_time(int hour, int min) {
		Date d = new Date();
		d.setHours(hour);
		d.setMinutes(min);
		return d;
	}

	public static Date date_plus_days(int days) {
		Date d = new Date();
		d.setDate(d.getDate()+days);
		return d;
	}

	public static Date get_tomorrow() {
		return date_plus_days(1);
	}
}
