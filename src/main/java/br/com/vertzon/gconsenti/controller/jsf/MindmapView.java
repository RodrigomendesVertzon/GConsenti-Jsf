package br.com.vertzon.gconsenti.controller.jsf;

import java.io.Serializable;
import java.util.UUID;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class MindmapView implements Serializable {
	private static final long serialVersionUID = -7028027974541972129L;

	private MindmapNode root;
    private MindmapNode selectedNode;
     
    @SuppressWarnings("unused")
	public MindmapView() {
        
    	root = new DefaultMindmapNode("CPF", "CPF - Dado do usuário", "FFCC00", false);
        
    	MindmapNode rg = new DefaultMindmapNode("RG", "RG - Dado do usuário", "FFCC00", true);
    	MindmapNode email = new DefaultMindmapNode("E-mail", "E-mail - Dado do usuário", "FFCC00", true);
    	MindmapNode sobrenome = new DefaultMindmapNode("Sobrenome", "E-mail - Dado do usuário", "FFCC00", true);
    	MindmapNode Nome = new DefaultMindmapNode("Nome", "E-mail - Dado do usuário", "FFCC00", true);
    	MindmapNode daNascimento = new DefaultMindmapNode("Data de Nascimento", "Data de Nascimento - Dado do usuário", "FFCC00", true);


    	
    	MindmapNode ips = new DefaultMindmapNode("DataBaseLearning", "Database CA SDM", "6e9ebf", true);
        MindmapNode ns = new DefaultMindmapNode("DB_RH", "Banco RH", "6e9ebf", true);
        MindmapNode malware = new DefaultMindmapNode("DataBaseLearning", "Banco sistema de clientes", "6e9ebf", true);
        MindmapNode orcl_clientes = new DefaultMindmapNode("GConsenti", "Banco sistema de clientes", "6e9ebf", true);
        MindmapNode mdb_2 = new DefaultMindmapNode("DataBaseLearning", "Banco sistema de clientes", "6e9ebf", true);
        
        
     
        root.addNode(malware);
        root.addNode(ns);
        root.addNode(orcl_clientes);
        
        malware.addNode(rg);
        malware.addNode(email);
        malware.addNode(Nome);
        malware.addNode(sobrenome);
        
   
    
    }
 
    public MindmapNode getRoot() {
        return root;
    }
 
    public MindmapNode getSelectedNode() {
        return selectedNode;
    }
    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }
 
    public void onNodeSelect(SelectEvent<MindmapNode> event) {
        MindmapNode node = event.getObject();
         
        //populate if not already loaded
        if(node.getChildren().isEmpty()) {
            Object label = node.getLabel();
 
            if(label.equals("NS(s)")) {
                for(int i = 0; i < 25; i++) {
                    node.addNode(new DefaultMindmapNode("ns" + i + "CPF", "Namespace " + i + " of Google", "82c542", false));
                }
            }
            else if(label.equals("IPs")) {
                for(int i = 0; i < 18; i++) {
                    node.addNode(new DefaultMindmapNode("1.1.1."  + i, "IP Number: 1.1.1." + i, "fce24f", false));
                } 
            }
            else if(label.equals("Malware")) {
                for(int i = 0; i < 18; i++) {
                    String random = UUID.randomUUID().toString();
                    node.addNode(new DefaultMindmapNode("Malware-"  + random, "Malicious Software: " + random, "3399ff", false));
                }
            }
        }   
    }
     
    public void onNodeDblselect(SelectEvent<MindmapNode> event) {
        this.selectedNode = event.getObject();
    }
 }