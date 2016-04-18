package fr.irit.smac.amasfactory.factoryclientdemo;

import com.hazelcast.core.Hazelcast;

import fr.irit.smac.amasfactory.util.IHazelcastKnowledgeAccessor;
import fr.irit.smac.amasfactory.util.hazelcast.impl.HazelcastKnowledgeAccessor;

public class UglyHazelcastObserver {

    private UglyHazelcastObserver() {

    }

    public static final void main(String[] args) {

        IHazelcastKnowledgeAccessor accessor = new HazelcastKnowledgeAccessor();

        for (String id : accessor.getKnowledgeIdSet()) {
            System.out.println("id=" + id + "\tknowledge=" + accessor.getKnowledge(id) + "\tknoID="
                + accessor.getKnowledge(id).getId());
        }
        // accessor.shutdownInstance();
        Hazelcast.shutdownAll();
    }
}
