using System;
using System.IO;
using System.Net;
using System.Text;

namespace DotNetSoapClient
{
    internal class Program
    {
        public static void Main(string[] args)
        {
           GetAllComptesList();
           GetOneCompte(2);
           Convert(126);
        }

        public static void GetAllComptesList()
        {
            string getComptesList =
                @"<?xml version=""1.0"" encoding=""utf-8""?>
                <soapenv:Envelope xmlns:soapenv=""http://schemas.xmlsoap.org/soap/envelope/""
                xmlns:ser=""http://service.web.ws.jax.com/"">
                <soapenv:Header/>
                <soapenv:Body>
                <ser:compteList/>
                </soapenv:Body>
                </soapenv:Envelope>";
            
            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create("http://localhost:8080/ws");
            httpWebRequest.ContentType = "text/xml;charset=\"utf-8\"";
            httpWebRequest.Accept = "text/xml";
            httpWebRequest.Method = "POST";

            using (Stream stm = httpWebRequest.GetRequestStream())
            {
                using (StreamWriter streamWriter = new StreamWriter(stm))
                {
                    streamWriter.Write(getComptesList);
                }
            }

            WebResponse response = httpWebRequest.GetResponse();

            using (Stream stream = response.GetResponseStream())
            {
                if (stream != null)
                {
                    StreamReader reader = new StreamReader(stream, Encoding.UTF8);
                    String responseString = reader.ReadToEnd();
                    Console.WriteLine("The Response :\n");
                    Console.WriteLine(responseString);
                }

                Console.WriteLine("\n");
            } 
        }
        
        public static void GetOneCompte(int code)
        {
            string body=
                @"<soapenv:Envelope xmlns:soapenv=""http://schemas.xmlsoap.org/soap/envelope/"" xmlns:ser=""http://service.web.ws.jax.com/"">
                <soapenv:Header/>
                <soapenv:Body>
                <ser:getCompte>
                <code>"+code+@"</code>
                </ser:getCompte>
                </soapenv:Body>
                </soapenv:Envelope>";
            
            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create("http://localhost:8080/ws");
            httpWebRequest.ContentType = "text/xml;charset=\"utf-8\"";
            httpWebRequest.Accept = "text/xml";
            httpWebRequest.Method = "POST";

            using (Stream stm = httpWebRequest.GetRequestStream())
            {
                using (StreamWriter streamWriter = new StreamWriter(stm))
                {
                    streamWriter.Write(body);
                }
            }

            WebResponse response = httpWebRequest.GetResponse();

            using (Stream stream = response.GetResponseStream())
            {
                if (stream != null)
                {
                    StreamReader reader = new StreamReader(stream, Encoding.UTF8);
                    String responseString = reader.ReadToEnd();
                    Console.WriteLine("The Response :\n");
                    Console.WriteLine(responseString);
                }

                Console.WriteLine("\n");
            } 
        }
        
        
        public static void Convert(double montant)
        {
            string body=
                @"<soapenv:Envelope xmlns:soapenv=""http://schemas.xmlsoap.org/soap/envelope/"" xmlns:ser=""http://service.web.ws.jax.com/"">
                <soapenv:Header/>
                <soapenv:Body>
                <ser:Convert>
                <montant>"+montant+@"</montant>
                </ser:Convert>
                </soapenv:Body>
                </soapenv:Envelope>";
            
            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create("http://localhost:8080/ws");
            httpWebRequest.ContentType = "text/xml;charset=\"utf-8\"";
            httpWebRequest.Accept = "text/xml";
            httpWebRequest.Method = "POST";

            using (Stream stm = httpWebRequest.GetRequestStream())
            {
                using (StreamWriter streamWriter = new StreamWriter(stm))
                {
                    streamWriter.Write(body);
                }
            }

            WebResponse response = httpWebRequest.GetResponse();

            using (Stream stream = response.GetResponseStream())
            {
                if (stream != null)
                {
                    StreamReader reader = new StreamReader(stream, Encoding.UTF8);
                    String responseString = reader.ReadToEnd();
                    Console.WriteLine("The Response :\n");
                    Console.WriteLine(responseString);
                }

                Console.WriteLine("\n");
            } 
        }
    }
}