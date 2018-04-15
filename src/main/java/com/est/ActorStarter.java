package com.est;

import java.io.IOException;

import com.est.Greeter.Greet;
import com.est.Greeter.WhoToGreet;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

/**
 * @author estevam
 */
public class ActorStarter {

	public void start() {
		final ActorSystem system = ActorSystem.create("helloakka");
		try {
			final ActorRef printerActor = system.actorOf(Printer.props(), "printerActor");
			final ActorRef howdyGreeter = system.actorOf(Greeter.props("Howdy", printerActor), "howdyGreeter");
			final ActorRef helloGreeter = system.actorOf(Greeter.props("Hello", printerActor), "helloGreeter");
			final ActorRef goodDayGreeter = system.actorOf(Greeter.props("Good day", printerActor), "goodDayGreeter");

			howdyGreeter.tell(new WhoToGreet("Akka"), ActorRef.noSender());
			howdyGreeter.tell(new Greet(), ActorRef.noSender());

			howdyGreeter.tell(new WhoToGreet("Lightbend"), ActorRef.noSender());
			howdyGreeter.tell(new Greet(), ActorRef.noSender());

			helloGreeter.tell(new WhoToGreet("Java"), ActorRef.noSender());
			helloGreeter.tell(new Greet(), ActorRef.noSender());

			goodDayGreeter.tell(new WhoToGreet("Play... No spring :P "), ActorRef.noSender());
			goodDayGreeter.tell(new Greet(), ActorRef.noSender());

			// System.out.println(">>> Press ENTER to exit <<<");
			System.in.read();
		} catch (IOException ioe) {
		} finally {
			system.terminate();
		}
	}

}
