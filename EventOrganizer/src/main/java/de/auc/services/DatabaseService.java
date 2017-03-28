package de.auc.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.auc.model.Event;
import de.auc.model.Reservation;
import de.auc.model.User;

/**
 * Dieser Service dient der Erstellung des Entitymanagers.
 *
 */
@ApplicationScoped
public class DatabaseService implements Serializable {

	private static final long serialVersionUID = 8077242953068886253L;
	@Inject
	private EntityManager entityManager;

	@Produces
	public EntityManager createEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2");
		return emf.createEntityManager();
	}

	/**
	 * Überprüft, ob Inhalte in der Datenbank vorhanden sind. Beispielhaft wird
	 * die Tabelle User durchsucht. Ist diese gefüllt, kann davon ausgegangen
	 * werden, dass auch Events existieren aufgrund der obigen
	 * PostConstruct-Methode.
	 * 
	 * @return
	 */
	public boolean getContentTables() {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);

		List<User> users = query.getResultList();
		if (users.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	@PostConstruct
	public void initEventOrganizerData() {
		if (getContentTables()) {
			Calendar today = Calendar.getInstance();

			// TODO: Diese User nach dem Testen entfernen!
			User testUser = new User("Claudia", "Schaefers", new Date(System.currentTimeMillis()), "cs@jee.de", "123",
					true);
			User testUser1 = new User("a", "a", new Date(System.currentTimeMillis()), "a", "a", true);
			User testUser2 = new User("b", "b", new Date(System.currentTimeMillis()), "b", "b", false);

			/** Testdaten: Benutzer */
			today.set(1976, 10, 16);
			User manager1 = new User("Manager", "Thomas", today.getTime(), "Thomas.Manager@eventfull.de", "Event123",
					true);
			today.set(1988, 4, 20);
			User manager2 = new User("Managment", "Sabine", today.getTime(), "Sabine_Management@eventtickets.de",
					"Tickets23", true);
			today.set(1994, 7, 28);
			User user1 = new User("Fanfull", "Karl", today.getTime(), "Fanfull-Karl@web.de", "Musik287", false);
			today.set(1975, 9, 12);
			User user2 = new User("Musikliebe", "Leona", today.getTime(), "musikliebe@gmx.de", "Leona129", false);

			/** Testdaten: Event */
			today.set(2017, 10, 15);
			Event event1 = new Event("Mario Barth: Männer sind bekloppt, aber sexy!",
					"Ein Stück Beklopptheit macht Männer sexy! Wer könnte davon überzeugender erzählen als Mario Barth? "
							+ "Wenn Deutschlands erfolgreichster Comedian seine Alltagsgeschichten mit unverwechselbarer Mimik zum Besten "
							+ "gibt und absurde Dialoge lebhaft nachspielt, erweist er sich als extrem guter Beobachter – nicht nur in dem "
							+ "Beziehungsalltag von Männer und Frauen. In seinen Geschichten steckt immer ein hoher Wiedererkennungswert für "
							+ "seine Zuschauer. Kein Wunder, dass der Angriff auf die Lachmuskeln zwei Stunden lang nicht zu stoppen ist. "
							+ "Mehr als 600.000 begeisterte Fans haben das aktuelle Programm „Männer sind bekloppt, aber sexy“ bereits erlebt.",
					"Münster", today.getTime(), 1250, 39.95, true, manager1);
			today.set(2017, 5, 8);
			Event event2 = new Event("Luke Mockridge: Lucky Man",
					"Lucky Luke – wer bei diesem Namen an einen einsamen Comic-Cowboy denkt, ist hier auf der falschen Fährte. "
							+ "„Komik-Cowboy” trifft es da wohl eher. Die Rede ist natürlich von Luke Mockridge, dem supertalentierten "
							+ "Comedian, der 2012 mit seinem Stand-up-Programm „Im lucky, I’m Luke” wie ein Komet in der deutschen Comedyszene "
							+ "einschlug und das Publikum seither mit seinem unwiderstehlichen Charme begeistert. Wo immer Luke auftritt, "
							+ "feiern die Leute ihn ab. Tausende haben bereits Tränen gelacht, wenn er auf der Bühne die Neunzigerjahre Revue "
							+ "passieren lässt, über seine Familie oder seine Ex plaudert und Alltagssituationen beschreibt, in denen sich "
							+ "jeder sofort wiederfindet. Nicht nur auf der Comedy-Bühne ist das charmante Energiebündel zu Hause, nein, "
							+ "Luke Mockridge hat viele Talente – er ist Moderator, Autor, Musiker, YouTuber und hat eine eigene Fernsehshow. "
							+ "Trotz seiner Riesenerfolge denkt der Sonnyboy nicht im Traum daran, sich darauf auszuruhen. Im Gegenteil, "
							+ "ein neues Programm hat er schon in der Pipeline: 2017 entert er mit „Lucky Man” erneut zahlreiche Locations "
							+ "deutschlandweit. Denn am allerliebsten bringt er sein Publikum live zum Lachen.",
					"Dortmund", today.getTime(), 5200, 29.95, true, manager1);
			today.set(2018, 2, 8);
			Event event3 = new Event("Chris Tall: Selfie von Mutti! Wenn Eltern cool sein wollen",
					"Er ist der Shooting Star der Deutschen Comedy-Szene: Chris Tall! Mit seinem aktuellen Programm "
							+ "\"Selfie von Mutti! Wenn Eltern cool sein wollen...\" ist der Hamburger quer durch Deutschland unterwegs "
							+ "und darf sich über volle Hallen freuen! Mit seinem perfekten Gespür für Timing feuert Chris seine Pointen "
							+ "ins Publikum und seziert genüsslich den modernen Generationen-Konflikt. Zwischen Fürsorge und Facebook – da "
							+ "ist Stress vorprogrammiert! Seit seinem ersten Programm „Versetzung gefährdet“ im Jahr 2013 geht es für den "
							+ "24-Jährigen steil bergauf. Nach etlichen Auszeichnungen (u.a. „RTL Comedy Grand Prix“) folgten Auftritte bei "
							+ "„TV total“ (PROSIEBEN), „Bülent und seine Freunde“ (RTL) oder „Willkommen bei Mario Barth“ (RTL); auf der "
							+ "Kinoleinwand brillierte Chris Tall 2015 bei „Abschussfahrt“. In diesem Jahr steht er mit „Selfie von Mutti!“ "
							+ "120 Mal auf der Bühne – nur noch wenige Tickets sind verfügbar. Zur Freude der Fans geht es 2017 durch die "
							+ "großen Hallen der Republik weiter, u.a. in der Kölner LANXESS arena, der König-Pilsener Arena in Oberhausen "
							+ "und der Barclaycard Arena in Hamburg. Mit \"Selfie von Mutti! Wenn Eltern cool sein wollen...\" weist "
							+ "Chris Tall der Generation YouTube den Weg durch den familiären Wahnsinn. Wehe, Papa geht mobile und verliert "
							+ "sich im App-Dschungel. Oder Mama begrüßt die neue Freundin mit „Was geht, Schwester?“. Zwischen Pausenbrot "
							+ "und Pickelsalbe bekommen auch Randgruppen ihr Fett weg: Stand-Up für Rollstuhlfahrer – #darferdas? Ja. "
							+ "Denn Chris lässt die eigene Fehlbarkeit nicht aus dem Blick.Und am Ende wissen alle im Saal: Eltern sind "
							+ "peinlich. Und Mutti ist die Beste! ",
					"Bielefeld", today.getTime(), 2050, 28.25, false, manager1);
			today.set(2018, 3, 25);
			Event event4 = new Event("Bülent Ceylan: Kronk",
					"Die Welt geht am Stock, die Menschen sind dringend auf humoristische Behandlung angewiesen und die bekommen "
							+ "sie direkt und in gewohnt hoher Dosis bei Bülent Ceylan's neuem Programm: „KRONK“. Vor allem Körper, Geist "
							+ "und jede Menge absurder Situationen können heutzutage krank sein. Selbst geistreiche Menschen werden oft "
							+ "durch sehr abstruse Meinungen infiziert und leiden an Intoleranz- Symptomen, an Respektlosigkeit und "
							+ "mangelnder Nächstenliebe. Krank oder wie eben der Mannheimer sagt: „KRONK“, sind aber vor allem auch "
							+ "Situationen, in die wir täglich geraten. Und die werden in gewohnter Art und Weise von Bülent Ceylan intensiv "
							+ "untersucht, professionell diagnostiziert und mit dem besten Heilmittel der Welt, dem herzhaften Lachen "
							+ "behandelt. Bülent Ceylan kümmert sich um die kleinen Wehwehchen genau so intensiv wie um die bekannten "
							+ "Epidemien unserer Zeit. Seine Therapie ist dabei besonders heilsam und erprobt, wenn auch der ein oder andere "
							+ "„Krankheitserreger“ durchaus mal bittere Pillen schlucken muss. Da werden Krankheiten abgeklopft wie die "
							+ "aktuell sich immer weiter ausbreitenden, fremdenfeindlichen Seuchen, die durchaus öfter auftretende Herz- und "
							+ "Rückgratlosigkeit und es wird dem ein oder anderen Großmaul der Lügenbeutel entfernt! Bülent Ceylan bevorzugt "
							+ "auch dieses Mal die direkte und klare Sprache, eine äusserst gesunde Mischung aus sehr ernsthafter Wahrnehmung, "
							+ "phantasie- und humorvoller Diagnose und einigen Behandlungsempfehlungen mit großen Heilungschancen. Besonders "
							+ "gespannt sein dürfen die Zuschauer dieses Mal bei „KRONK“ wieder auf die fachlichen Analysen und Ratschläge "
							+ "seiner Freunde Harald, Hasan, Anneliese und Mompfreed Bockenauer. Respekt ist sein Gesundbrunnen, Nächstenliebe "
							+ "und Lachen seine Medizin und wenn dann doch einem Patienten mal etwas weh tut, dann ist es der Bauch oder das "
							+ "Zwerchfell. Und das ist dann ein Wohlweh der besten Sorte und immer auch eine Behandlung, auf die jeder Patient "
							+ "sofort anspricht!",
					"München", today.getTime(), 900, 36.60, false, manager1);
			today.set(2017, 9, 18);
			Event event5 = new Event("Helene Fischer - Live 2017/2018",
					"Was Helene Fischer auch anfasst, es wird so golden wie ihre Haare – und dabei steht die sympathische "
							+ "Schlager-Ikone, die schon jetzt ein eigenes Zimmer für all ihre gewonnenen Echos und Goldenen Schallplatten "
							+ "braucht, gerade mal am Anfang ihrer Karriere. Keine Frage: Die Zukunft der deutschen Pop- und Schlager-Musik "
							+ "gehört Helene Fischer! 2017 können sich die Fans auf etwas ganz und gar Bombastisches freuen: Im September "
							+ "begibt sich Helene Fischer auf eine epische Arena-Tour, die sie bis März 2018 durch Deutschland, Österreich "
							+ "und die Schweiz führen wird: 13 Städte wird der deutsche Popsuperstar dabei besuchen und dort jeweils fünf "
							+ "Shows spielen - das ergibt eine epische Anzahl von 65 Live-Dates! Inzwischen hat die Sängerin sogar noch vier "
							+ "zusätzliche Termine in Köln und Berlin Anfang 2018 bestätigt.",
					"Hamburg", today.getTime(), 4000, 94.80, true, manager2);
			today.set(2017, 8, 10);
			Event event6 = new Event("Von Wegen Lisbeth: Hallo Dispo Tour 2017",
					"Als „aufstrebende Berliner Indie-Band“ werden sie gern von der Presse betitelt. Das kann so nicht ganz korrekt "
							+ "sein, schließlich müssten sie dazu möglichst aus Großbritannien, Australien oder wenigstens Mühlheim an der "
							+ "Ruhr nach Berlin gezogen sein, um nach drei Monaten Berghain auf Labelkosten zwar völlig unzurechnungsfähig, "
							+ "dafür aber „total angekommen“ zu sein in dieser „totally inspirierenden“ Stadt. Die fünf Jungs von Von Wegen "
							+ "Lisbeth sind glücklicherweise ungewöhnlich anders. Kein Andreas Bourani. Kein Becks Ice. Kein Döner außerhalb "
							+ "von Berlin. Eher so Bärwaldpark. Von Wegen Lisbeth selbst bezeichnen ihre Musik als Indie-Pop. Größtenteils "
							+ "wohl, weil sich dahinter alles verbergen kann. Wie soll man deutschsprachige Musik denn auch nennen, zu der "
							+ "neben der klassischen Gitarre-Schlagzeug-Bass-Instrumentierung auch ein Regenbogenachttästler (buntes "
							+ "Metallophon, empfohlen ab drei Jahren), ein japanisches Omnichord (elektrische Harfe, Wert 2,50€) oder eine "
							+ "Steeldrum (vergleiche: chinesische Wokpfanne) gehört? Dazu die feinsinnigen Texte von Sänger Matze, stets "
							+ "irgendwas zwischen bitterer Ironie, Großstadtmelancholie und brutaler Punchline. Außerdem immer entlarvend "
							+ "pointiert - und natürlich immer über Mädchen.",
					"Osnabrück", today.getTime(), 800, 19.25, true, manager2);
			today.set(2018, 5, 16);
			Event event7 = new Event("Lena",
					"Seit dem Eurovision Song Contest 2010 kennt jeder ihren Namen. Ihre Alben sind ein Garant für Gold- und "
							+ "Platinstatus und ihre Hits sind unter den Top 10 der deutschen Singlecharts nicht mehr wegzudenken: unser "
							+ "Fräuleinwunder Lena Meyer-Landrut. Die in Hannover geboren und aufgewachsene Lena macht schon während der "
							+ "Schulzeit Ballett, Hip Hop, Show-Dance und Musik. Im Herbst 2009 bewirbt sich die damals 18-Jährige bei der "
							+ "Castingshow „Unser Star für Oslo“, dem deutschen Vorentscheid zum Eurovision Song Contest 2010. Sie gewinnt "
							+ "den Wettbewerb mit dem Lied „Satellite“, erhält einen Plattenvertrag bei Universal Music und vertritt "
							+ "Deutschland beim Eurovision Song Contest in Oslo. „Satellite“ schießt direkt auf Platz 1 der deutschen "
							+ "Single-Charts und Lena gewinnt für Deutschland Europas größten Musikwettbewerb. Im gleichen Jahr veröffentlicht "
							+ "sie ihr Debütalbum „My Cassette Player“, das ebenfalls sofort auf Platz 1 der deutschen Charts landet, leiht "
							+ "einer Meeresschildkröte im Film „Sammys Abenteuer“ ihre Stimme, trägt sich in das Goldene Buch der Stadt "
							+ "Hannover ein und wird mit Preisen überhäuft: 2011 erhält Lena die „Goldene Kamera“, den „Echo“, den „Cometen“ "
							+ "und den „MTV Europe Music Award“. Im Februar des Jahres veröffentlicht Lena ihr zweites Studioalbum "
							+ "„Good News“, das es an die Spitze der deutschen Charts schafft und nach einer Woche Goldstatus erreicht. "
							+ "Im Mai tritt die quirlige Hannoveranerin erneut für Deutschland beim Eurovision Song Contest zur "
							+ "Titelverteidigung in Düsseldorf an und belegt mit dem vom Publikum gewählten Lied „Taken by a Stranger“ "
							+ "den 10. Platz. Im Juni bestätigte Lena, dass sie an ihrem fünften Album arbeitet, welches 2017 erscheinen soll. "
							+ "Zudem wird sie 2017 erneut in der Jury des Eurovision Song Contests sowie in der vierten Staffel der "
							+ "VOX-Musik-Event-Reihe „Sing meinen Song - Das Tauschkonzert“ mit den Gastgebern „The BossHoss“ zu sehen sein. "
							+ "Ein aufregendes Jahr steht ihr also bevor. Zum krönenden Abschluss wird sie dann auf Tournee gehen.",
					"Stuttgart", today.getTime(), 1500, 41.70, false, manager2);
			today.set(2018, 3, 26);
			Event event8 = new Event("Clueso: Neuanfang",
					"Wenn man seit mehr als 20 Jahren Musik macht, ist es irgendwann vielleicht Zeit für einen „Neuanfang”. Bei "
							+ "Clueso jedenfalls ist dieser Punkt 2016 erreicht. Alles überdenken, die Weichen auf Anfang stellen, sich von "
							+ "langjährigen Wegbegleitern verabschieden und von vorne beginnen – genau das macht Clueso, und er macht es auf "
							+ "seine Art: mit einem neuen Album, das er passenderweise „Neuanfang” nennt, und einer ausgedehnten Tour. Denn "
							+ "auch wenn er sich von seiner Band und seinem Manager getrennt hat, ist die Musik eine feste Konstante in "
							+ "Cluesos Leben. Thomas Hübner, wie Clueso eigentlich heißt, kann gar nicht anders, als seine Gefühle in Musik "
							+ "auszudrücken. Wenn ihn etwas beschäftigt, greift er zur Gitarre und singt seine Gedanken heraus. Texte und "
							+ "Melodien sind sein Zugang zu allem, was um ihn herum passiert – er erklärt sich die Welt durch Musik und teilt "
							+ "sich ihr auf gleiche Weise mit. Clueso ist eben mit Leib und Seele Musiker. Da ist es wenig verwunderlich, "
							+ "dass er schon als Teenager damit anfing.",
					"Berlin", today.getTime(), 1800, 44.90, false, manager2);

			/** Testdaten: Reservierung */
			
			Reservation reservation1 = new Reservation(UUID.randomUUID().toString(), 6, user1, event1);
			Reservation reservation2 = new Reservation(UUID.randomUUID().toString(), 2, user1, event2);
			Reservation reservation3 = new Reservation(UUID.randomUUID().toString(), 4, user2, event5);
			Reservation reservation4 = new Reservation(UUID.randomUUID().toString(), 5, user2, event6);
		

			try {
				/** Testdaten: Benutzer - DB-Import */
				entityManager.getTransaction().begin();

				// TODO: raus nehmen
				entityManager.persist(testUser);
				entityManager.persist(testUser1);
				entityManager.persist(testUser2);

				entityManager.persist(manager1);
				entityManager.persist(manager2);
				entityManager.persist(user1);
				entityManager.persist(user2);
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}

			try {
				/** Testdaten: Event - DB-Import */
				entityManager.getTransaction().begin();
				entityManager.persist(event1);
				entityManager.persist(event1.getUser());
				entityManager.persist(event2);
				entityManager.persist(event2.getUser());
				entityManager.persist(event3);
				entityManager.persist(event3.getUser());
				entityManager.persist(event4);
				entityManager.persist(event4.getUser());
				entityManager.persist(event5);
				entityManager.persist(event5.getUser());
				entityManager.persist(event6);
				entityManager.persist(event6.getUser());
				entityManager.persist(event7);
				entityManager.persist(event7.getUser());
				entityManager.persist(event8);
				entityManager.persist(event8.getUser());
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}

			try {
				/** Testdaten: Reservierung - DB-Import */
				entityManager.getTransaction().begin();
				entityManager.persist(reservation1);
				entityManager.persist(reservation1.getEvent());
				entityManager.persist(reservation1.getUser());
				entityManager.persist(reservation2);
				entityManager.persist(reservation2.getEvent());
				entityManager.persist(reservation2.getUser());
				entityManager.persist(reservation3);
				entityManager.persist(reservation3.getEvent());
				entityManager.persist(reservation3.getUser());
				entityManager.persist(reservation4);
				entityManager.persist(reservation4.getEvent());
				entityManager.persist(reservation4.getUser());
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}
		}
	}
}
