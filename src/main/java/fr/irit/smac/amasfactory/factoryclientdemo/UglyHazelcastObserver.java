package fr.irit.smac.amasfactory.factoryclientdemo;


import fr.irit.smac.amasfactory.util.impl.HazelcastKnowledgeAccessor;

public class UglyHazelcastObserver {
	public static final void main(String[] args) {
		
		HazelcastKnowledgeAccessor accessor = new HazelcastKnowledgeAccessor();
		
		
		for (String id : accessor.getKnowledgeIdSet()){
			System.out.println("id=" +id +"\tknowledge=" + accessor.getKnowledge(id) +"\tknoID=" + accessor.getKnowledge(id).getId());
		}
	}
}
