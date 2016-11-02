/**
 * ********************************************************************************
 * Copyright (c) 2016, Semantic Computing Group, Bielefeld University All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met: *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. * Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. * Neither the name of the Monnet Project nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE MONNET PROJECT BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ********************************************************************************
 */
package de.citec.sc.lemon.utils;

import de.citec.sc.lemon.core.Language;
import de.citec.sc.lemon.core.LexicalEntry;
import de.citec.sc.lemon.core.Lexicon;
import de.citec.sc.lemon.core.Preposition;
import de.citec.sc.lemon.core.Provenance;
import de.citec.sc.lemon.core.Reference;
import de.citec.sc.lemon.core.Sense;
import de.citec.sc.lemon.core.SenseArgument;
import de.citec.sc.lemon.core.SimpleReference;
import de.citec.sc.lemon.core.SyntacticArgument;
import de.citec.sc.lemon.core.SyntacticBehaviour;

/**
 * 
 * @author swalter
 */
public class Templates {
	
   
	public static void create_NounPossessive_Entry(Lexicon lexicon,String noun, String e1_arg, String e2_arg, String reference, Language language) {
	        		 
            LexicalEntry entry = new LexicalEntry(language);

            Sense sense = new Sense();
            Reference ref = new SimpleReference(reference);
            sense.setReference(ref);

            Provenance provenance = new Provenance();
            provenance.setFrequency(1);


            SyntacticBehaviour behaviour = new SyntacticBehaviour();
            entry.setCanonicalForm(noun);
            entry.setURI(lexicon.getBaseURI()+"LexicalEntry_"+cleanTerm(noun)+"_as_PossessiveNoun");

            
            
            entry.setPOS("http://www.lexinfo.net/ontology/2.0/lexinfo#commonNoun");

            behaviour.setFrame("http://www.lexinfo.net/ontology/2.0/lexinfo#NounPossessiveFrame");

            // System.out.print(entry+"\n");

            if (e1_arg.equals("http://lemon-model.net/lemon#subjOfProp") && e2_arg.equals("http://lemon-model.net/lemon#objOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#directObject","object",null));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#subject","subject",null));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","subject"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","object"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(noun)){
                        lexicon.addEntry(entry);
                    }



            }	

            else if (e1_arg.equals("http://lemon-model.net/lemon#objOfProp") && e2_arg.equals("http://lemon-model.net/lemon#subjOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#directObject","subject",null));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#subject","object",null));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","object"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","subject"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(noun)){
                        lexicon.addEntry(entry);

                    }
            }
	        			 
	}
	
	
	
	public static void create_NounWithPrep_Entry(Lexicon lexicon, String noun, String e1_arg, String e2_arg, String preposition, String reference, Language language) {
	        		 
            LexicalEntry entry = new LexicalEntry(language);
            Sense sense = new Sense();
            entry.setPreposition(new Preposition(language,preposition));

            Reference ref = new SimpleReference(reference);
            sense.setReference(ref);

            Provenance provenance = new Provenance();
            provenance.setFrequency(1);


            SyntacticBehaviour behaviour = new SyntacticBehaviour();

            entry.setCanonicalForm(noun);
            entry.setURI(lexicon.getBaseURI()+"LexicalEntry_"+cleanTerm(noun)+"_as_Noun_withPrep_"+preposition);
            

            entry.setPOS("http://www.lexinfo.net/ontology/2.0/lexinfo#commonNoun");

            behaviour.setFrame("http://www.lexinfo.net/ontology/2.0/lexinfo#NounPPFrame");

            if (e1_arg.equals("http://lemon-model.net/lemon#subjOfProp") && e2_arg.equals("http://lemon-model.net/lemon#objOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#prepositionalObject","object",preposition));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#copulativeArg","subject",null));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","subject"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","object"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(noun)){
                        lexicon.addEntry(entry);
                    }



            }	

            else if (e1_arg.equals("http://lemon-model.net/lemon#objOfProp") && e2_arg.equals("http://lemon-model.net/lemon#subjOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#adpositionalObject","subject",preposition));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#copulativeArg","object",null));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","object"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","subject"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(noun)){
                        lexicon.addEntry(entry);
                    }

            }
	}
	
	
	public static void create_Adjective_Entry(Lexicon lexicon, String adjective, String e1_arg, String e2_arg, String preposition, String reference, Language language) {
	        		 
            LexicalEntry entry = new LexicalEntry(language);
            entry.setURI(lexicon.getBaseURI()+"LexicalEntry_"+cleanTerm(adjective)+"_as_Adjective_withPrep_"+preposition);
            entry.setPreposition(new Preposition(language,preposition));
            Sense sense = new Sense();

            Reference ref = new SimpleReference(reference);
            sense.setReference(ref);

            Provenance provenance = new Provenance();
            provenance.setFrequency(1);

            SyntacticBehaviour behaviour = new SyntacticBehaviour();

            entry.setCanonicalForm(adjective);

            entry.setPOS("http://www.lexinfo.net/ontology/2.0/lexinfo#adjective");

            behaviour.setFrame("http://www.lexinfo.net/ontology/2.0/lexinfo#AdjectivePredicateFrame");


            if (e1_arg.equals("http://lemon-model.net/lemon#subjOfProp") && e2_arg.equals("http://lemon-model.net/lemon#objOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#prepositionalObject","object",preposition));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#copulativeArg","subject",null));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","subject"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","object"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(adjective)){
                        lexicon.addEntry(entry);
                    }



            }	

            else if (e1_arg.equals("http://lemon-model.net/lemon#objOfProp") && e2_arg.equals("http://lemon-model.net/lemon#subjOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#adpositionalObject","subject",preposition));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#copulativeArg","object",null));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","object"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","subject"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(adjective)){
                        lexicon.addEntry(entry);
                    }
            }
	}
	
	
	
	
	public static void create_TransitiveVerb_Entry(Lexicon lexicon, String verb, String e1_arg, String e2_arg, String reference, Language language) {
            LexicalEntry entry = new LexicalEntry(language);

            

            Sense sense = new Sense();

            Reference ref = new SimpleReference(reference);
            sense.setReference(ref);

            Provenance provenance = new Provenance();
            provenance.setFrequency(1);


            SyntacticBehaviour behaviour = new SyntacticBehaviour();

            entry.setCanonicalForm(verb);
            entry.setURI(lexicon.getBaseURI()+"LexicalEntry_"+cleanTerm(verb)+"_as_TransitiveVerb");
            
            
            entry.setPOS("http://www.lexinfo.net/ontology/2.0/lexinfo#verb");

            behaviour.setFrame("http://www.lexinfo.net/ontology/2.0/lexinfo#TransitiveFrame");


            if (e1_arg.equals("http://lemon-model.net/lemon#subjOfProp") && e2_arg.equals("http://lemon-model.net/lemon#objOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#subject","subject",null));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#directObject","object",null));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","subject"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","object"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(verb)){
                        lexicon.addEntry(entry);
                    }

            }	

            else if (e1_arg.equals("http://lemon-model.net/lemon#objOfProp") && e2_arg.equals("http://lemon-model.net/lemon#subjOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#subject","object",null));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#directObject","subject",null));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","subject"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","object"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(verb)){
                        lexicon.addEntry(entry);
                    }
            }
	}
	
        public static void create_ReflexiveTransitiveVerb_Entry(Lexicon lexicon, String verb, String e1_arg, String e2_arg, String preposition, String reference, Language language) {

            LexicalEntry entry = new LexicalEntry(language);
            entry.setPreposition(new Preposition(language,preposition));
            Sense sense = new Sense();

            Reference ref = new SimpleReference(reference);
            sense.setReference(ref);

            Provenance provenance = new Provenance();
            provenance.setFrequency(1);


            SyntacticBehaviour behaviour = new SyntacticBehaviour();
            
            entry.setCanonicalForm(verb);
            entry.setURI(lexicon.getBaseURI()+"LexicalEntry_"+cleanTerm(verb)+"_as_ReflexiveTransitiveVerb_withPrep_"+preposition);
            
             
            entry.setPOS("http://www.lexinfo.net/ontology/2.0/lexinfo#verb");

            behaviour.setFrame("http://www.lexinfo.net/ontology/2.0/lexinfo#ReflexiveTransitivePPFrame");


            if (e1_arg.equals("http://lemon-model.net/lemon#subjOfProp") && e2_arg.equals("http://lemon-model.net/lemon#objOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#subject","subject",null));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#prepositionalObject","object",preposition));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","subject"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","object"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(verb)){
                        lexicon.addEntry(entry);
                    }
            }	

            else if (e1_arg.equals("http://lemon-model.net/lemon#objOfProp") && e2_arg.equals("http://lemon-model.net/lemon#subjOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#subject","object",null));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#prepositionalObject","subject",preposition));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","subject"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","object"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(verb)){
                        lexicon.addEntry(entry);
                    }
            }	
	}
                
    public static void create_ReflexiveTransitiveVerbWihoutPrep_Entry(Lexicon lexicon, String verb, String e1_arg, String e2_arg, String reference, Language language) {

            LexicalEntry entry = new LexicalEntry(language);
            Sense sense = new Sense();

            Reference ref = new SimpleReference(reference);
            sense.setReference(ref);

            Provenance provenance = new Provenance();
            provenance.setFrequency(1);


            SyntacticBehaviour behaviour = new SyntacticBehaviour();
            
            entry.setCanonicalForm(verb);
            entry.setURI(lexicon.getBaseURI()+"LexicalEntry_"+cleanTerm(verb)+"_as_ReflexiveTransitiveVerb_withoutPrep");
             
             
            entry.setPOS("http://www.lexinfo.net/ontology/2.0/lexinfo#verb");

            behaviour.setFrame("http://www.lexinfo.net/ontology/2.0/lexinfo#ReflexiveTransitiveFrame");


            if (e1_arg.equals("http://lemon-model.net/lemon#subjOfProp") && e2_arg.equals("http://lemon-model.net/lemon#objOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#subject","subject",null));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#prepositionalObject","object",null));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","subject"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","object"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(verb)){
                        lexicon.addEntry(entry);

                        //logger.debug("Found entry:"+entry+"\n");
                    }
                    else{
                        //logger.debug("Dit not add entry, beause of label: "+verb);
                    }


            }	

            else if (e1_arg.equals("http://lemon-model.net/lemon#objOfProp") && e2_arg.equals("http://lemon-model.net/lemon#subjOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#subject","object",null));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#prepositionalObject","subject",null));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","subject"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","object"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(verb)){
                        lexicon.addEntry(entry);
                    }
            }	
	}
                        
	public static void create_IntransitiveVerb_Entry(Lexicon lexicon, String verb, String e1_arg, String e2_arg, String preposition, String reference, Language language) {

            LexicalEntry entry = new LexicalEntry(language);
            entry.setPreposition(new Preposition(language,preposition));
            Sense sense = new Sense();

            Reference ref = new SimpleReference(reference);
            sense.setReference(ref);

            Provenance provenance = new Provenance();
            provenance.setFrequency(1);


            SyntacticBehaviour behaviour = new SyntacticBehaviour();
            
            
            entry.setCanonicalForm(verb);
            entry.setURI(lexicon.getBaseURI()+"LexicalEntry_"+cleanTerm(verb)+"_as_IntransitiveVerb_withPrep_"+preposition);
            
             
             
            entry.setPOS("http://www.lexinfo.net/ontology/2.0/lexinfo#verb");

            behaviour.setFrame("http://www.lexinfo.net/ontology/2.0/lexinfo#IntransitivePPFrame");


            if (e1_arg.equals("http://lemon-model.net/lemon#subjOfProp") && e2_arg.equals("http://lemon-model.net/lemon#objOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#subject","subject",null));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#prepositionalObject","object",preposition));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","subject"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","object"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(verb)){
                        lexicon.addEntry(entry);
                    }
            }	

            else if (e1_arg.equals("http://lemon-model.net/lemon#objOfProp") && e2_arg.equals("http://lemon-model.net/lemon#subjOfProp"))
            {

                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#subject","object",null));
                    behaviour.add(new SyntacticArgument("http://www.lexinfo.net/ontology/2.0/lexinfo#prepositionalObject","subject",preposition));

                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#subjOfProp","subject"));
                    sense.addSenseArg(new SenseArgument("http://lemon-model.net/lemon#objOfProp","object"));

                    entry.addSyntacticBehaviour(behaviour,sense);
                    entry.addProvenance(provenance,sense);

                    if(isAlpha(verb)){
                        lexicon.addEntry(entry);
                    }
            }	
	}
        
        
        
        
        
        
        
        
        private static boolean isAlpha(String label) {
            if(label.length()<=2) return false;
            label = cleanTerm(label);
            label = label.replace("_","");
            char[] chars = label.toCharArray();

            for (char c : chars) {
                if(!Character.isLetter(c)) {
                    return false;
                }
            }
            return true;
        }
        
        
        private static String cleanTerm(String input){
            String output = input.replace("ü", "ue")
                    .replace("ö", "oe")
                    .replace("ß", "ss")
                    .replace("-", "_")
                    .replace(" ", "_")
                    .replace("\"", "")
                    .replace("+", "_");
            return output;
        }
	

}