/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package langtonsant;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alex
 */
public class AntRuleSet {

    private HashMap<Integer, HashMap<Integer, Rule>> rules;

    public AntRuleSet() {
        rules = new HashMap<Integer, HashMap<Integer, Rule>>();
    }

    public void addRule(int state, int colour, int newColour, int direction, int newState) {
        if (!rules.containsKey(state)) {
            rules.put(state, new HashMap<Integer, Rule>());
        }
        rules.get(state).put(colour, new Rule(newColour, direction, newState));
    }

    public void printRules() {
//        for (Map.Entry<Integer, HashMap<Integer, Rule>> m : rules.entrySet()) {
//            for (Map.Entry<Integer, Rule> r : m.getValue().entrySet()) {
//                System.out.println("{" + m.getKey() + ", " + r.getKey() + ", " + r.getValue().getColour() + ", " + r.getValue().getDirection() + ", " + r.getValue().getState() + "}");
//            }
//        }
        System.out.println(getRulesAsText());
    }
    
    public String getRulesAsText() {
        StringBuilder s = new StringBuilder();
        for (Map.Entry<Integer, HashMap<Integer, Rule>> m : rules.entrySet()) {
            for (Map.Entry<Integer, Rule> r : m.getValue().entrySet()) {
                s.append('{');
                s.append(m.getKey());
                s.append(", ");
                s.append(r.getKey());
                s.append(", ");
                s.append(r.getValue().getColour());
                s.append(", ");
                s.append(r.getValue().getDirection());
                s.append(", ");
                s.append(r.getValue().getState());
                s.append("}\n");
                //System.out.println("{" + m.getKey() + ", " + r.getKey() + ", " + r.getValue().getColour() + ", " + r.getValue().getDirection() + ", " + r.getValue().getState() + "}");
            }
        }
        return s.toString();
    }

    Rule getRule(int state, int colour) {
        return rules.get(state).get(colour);
    }
}

class Rule {

    private int c;
    private int s;
    private int d;

    public Rule(int newColour, int direction, int newState) {
        c = newColour;
        s = newState;
        d = direction;
    }

    public int getColour() {
        return c;
    }

    public int getState() {
        return s;
    }

    public int getDirection() {
        return d;
    }
}
