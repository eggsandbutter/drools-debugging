package test;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.AgendaGroupPoppedEvent;
import org.kie.api.event.rule.AgendaGroupPushedEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleFlowGroupActivatedEvent;
import org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sample.ObjectA;
import com.sample.ObjectB;

public class TestRule {

	@Test
	public void test() {
		KieSession kieSession = null;
		try {
			KieServices kieServices = KieServices.Factory.get();
			KieContainer kieContainer = kieServices.getKieClasspathContainer();
			kieSession = kieContainer.newKieSession("ksession-rules");
	        kieSession.addEventListener(new RuleRuntimeEventListener() {
	 
	        	public void objectInserted(ObjectInsertedEvent objectInsertedEvent) {
					System.out.println("INSERT: "+ objectInsertedEvent.getObject().toString());				
	            }
	            public void objectUpdated(ObjectUpdatedEvent objectUpdatedEvent) {
					System.out.println("UPDATE: "+ objectUpdatedEvent.getObject().toString());				
	            }
	            public void objectDeleted(ObjectDeletedEvent objectDeletedEvent) {
					System.out.println("DELETE: "+ objectDeletedEvent.getOldObject().toString());				
	            }
	        });
	        kieSession.addEventListener(new AgendaEventListener() {
				
	            public void matchCreated(MatchCreatedEvent event) {
					System.out.println(event.getMatch().getRule().getName()+ " can be fired in agenda");				
	            }
	            public void matchCancelled(MatchCancelledEvent event) {
					System.out.println(event.getMatch().getRule().getName()+ " cannot be in agenda");				
	            }
				public void beforeMatchFired(BeforeMatchFiredEvent event) {
					System.out.println(event.getMatch().getRule().getName()+ " will be fired");				
	            }
	            public void afterMatchFired(AfterMatchFiredEvent event) {
					System.out.println(event.getMatch().getRule().getName()+ " has been fired");				
	            }
	            public void agendaGroupPopped(AgendaGroupPoppedEvent event) {
	            }
	            public void agendaGroupPushed(AgendaGroupPushedEvent event) {
	            }
	            public void beforeRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
	            }
	            public void afterRuleFlowGroupActivated(RuleFlowGroupActivatedEvent event) {
	            }
	            public void beforeRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
	            }
	            public void afterRuleFlowGroupDeactivated(RuleFlowGroupDeactivatedEvent event) {
	            }
	        });
	        
	        ObjectB objectB = new ObjectB();
	        objectB.setSomeVariable(null);
	        
	        ObjectA objectA = new ObjectA();
	        objectA.setSomeVariable(null);
	        objectA.setObjectB(objectB);
	    
	        kieSession.insert(objectA);
	        kieSession.fireAllRules();

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (kieSession != null) {
		        kieSession.dispose();
		        kieSession.destroy();
			}
		}
	}

}
