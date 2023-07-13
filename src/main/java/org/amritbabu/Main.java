package org.amritbabu;

import org.amritbabu.helpers.AddDataToSolr;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    static String urlString = "http://localhost:8983/solr";
    private static SolrClient client;

    static {
        client = new HttpSolrClient.Builder(urlString).build();
    }




    public static void main(String[] args) throws SolrServerException, IOException {
        //add data to collection
         /*
    schema
    first_name:String,
    last_name:String,
    genere:List<String>,
    langugae:List<String>
     */
        //uncomment for populating data
//        try {
//            addDataToSolr();
//        } catch (SolrServerException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println("program started");
        System.out.println("\n\n, genere:War");
        executeSolrQuery("genere:War");
        System.out.println("\n\n, genere:horrror");
        executeSolrQuery("genere:horror");
        System.out.println("\n\n, genere:horrror, language: italian");
        executeSolrQuery(("(genere:horror)&&(language:Italian)"));
        System.out.println("\n\n, language:Italian and spanish");
        executeSolrQuery("(language:italian)&&(language:spanish)");
        System.out.println("\n\n, american english, adventure");
        executeSolrQuery("(language:'American English')&&(genere:Adventure)");
        System.out.println("\n\n, name: similar to am");
        executeSolrQuery("first_name:'am~'");
        System.out.println("\n\n, last_name containing oo and genere War");
        executeSolrQuery("(last_name:*oo*)&&(genere:war)");
        System.out.println("\n\n, first name near to os");
        executeSolrQuery("first_name:'os'~0.5");

        System.out.println("\n\n, british english^10 genere:war^2");
        executeSolrQuery("(language:'British English'^10)OR(genere:War^2)");

        System.out.println("\n\n, language american english and genere romance");
        Map<String,String> query1 = new HashMap<>();
        query1.put("q","language:'American English'");
        query1.put("fq","NOT(genere:Romance)");
        executeSolrQuery(query1);

    }

    public static void executeSolrQuery(String query) throws SolrServerException, IOException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(query);
        final QueryResponse response = client.query("test6",solrQuery);
        List<Model> results = response.getBeans(Model.class);
        results.forEach( result -> System.out.println(result.toString()));
    }

    public static void executeSolrQuery(Map<String,String> query) throws SolrServerException, IOException {
        MapSolrParams mapSolrParams = new MapSolrParams(query);
        final QueryResponse response = client.query("test6",mapSolrParams);
        List<Model> results = response.getBeans(Model.class);
        results.forEach( result -> System.out.println(result.toString()));
    }




    public static void addDataToSolr() throws SolrServerException, IOException {
        AddDataToSolr addDataToSolr = new AddDataToSolr();
        addDataToSolr.generateData(client);
    }
}