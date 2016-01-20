                      Integrating The Healthcare Enterprise - 慈濟大學, 醫學資訊學系 (TCU MI)
 

  What is it? 
  -----------
        在龐大的健康保險赤字壓力下，要解決債務壓力不外乎兩種方法:開源、節流, 
    開源，簡而之提高對每個要保人的收費；
    節流，減少醫療資源的濫用，減少病患重複接受檢驗和提供資料，使醫療資源能充分的被利用!
    
        節流方面可透過電子病歷/個人健康記錄等系統並達到互通過程，能將電子病歷/個人健康記錄讓所有醫療機構共享，
    您可能過去在北部 A 醫院治療，因為出差或是移居到南部，你能透過線上自行瀏覽或是授權予以南部的 B 醫院能調閱您在北部 A 醫院的病歷！
    以減少重複檢查的醫療浪費外，並能讓當下的醫生了解您的過去病史，使其有更多資訊可以參考，有助於正確治療！
    
        Integrating The Healthcare Enterprise(簡稱 IHE)，醫療資訊整合技術，
	提出許多醫療資訊互通的技術架構 (http://www.ihe.net/IHE_Domains/)，
    本專案正實做 IT Infrastructure 資訊技術基礎建設包含 XDS、PIXPDQ、ATNA ... ，本專案屬於客戶端程式，
    須搭配伺服器端程式，推薦使用 OPENXDS、OPENATNA、OPENPIXPDQ 或者依照 IHE 公開測試工具測試。
 (參考連結：https://www.projects.openhealthtools.org/sf/projects/openexchange/)


  Contents 內容
  --------
  
	Integrating The Healthcare Enterprise 
	- Cross-Enterprise Document Sharing (XDS)：
		- Registry Stored Query [交易代號：ITI-18]：查詢文件/資料夾的詮釋資料 (metadata)
		- Provide And Register Document Set [交易代號：ITI-41]：上傳文件/資料夾
		- Retrieve Document Set [交易代號：ITI-43]：下載文件
	- Audit Trail and Node Authentication (ATNA)：
		- Record Audit Event [交易代號：ITI-20]：記錄各種交易明細
	- Patient Identifier Cross-Referencing (PIX)：
		- Patient Identity Feed [交易代號：ITI-8]：註冊/更新病患身份
		- PIXQuery [交易代號：ITI-9]：查詢病患身份
	- Patient Demographics Query (PDQ)：
		- Patient Demographics Query [交易代號：ITI-21]：查詢病患個人資料
	- Consistent Time (CT)：
		- Maintain Time [交易代號:ITI-1]：維護時間
	- Cross-Community Access (XCA):
	    - Cross Gateway Query [交易代號:ITI-38]：跨閘道查詢文件
	    - Cross Gateway Retrieve [交易代號:ITI-39]：跨閘道讀取文件

  Requirements 開發環境需求
  ------------

     JDK Version	
	 - supports JDK 1.6 or higher. 
	 
	 MAVEN Version	     
     - 3.0.4 or higher.
        
     TOMCAT Version
     - 6.0.36 or higher.   

  Installation and Configuration
  ------------------------------
  
	 mvn clean install -DskipTests

  
  Documentation
  -------------

   
  The Latest Version
  ------------------

  Problems
  ---------

	Our web page at  has pointers where you can post questions, report bugs or request features. 


  Licensing
  ---------

	This software is licensed under the terms you may find in the file named "LICENSE.txt" in this directory.
 
 
  Support
  ---------
	For commercial support, please contact foxb1249@gmail.com. 
  
