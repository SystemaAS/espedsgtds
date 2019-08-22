## eSpedsg - Tillfällig lagring
___

> "Ett krav på bokföringen är att det finns en **spårbarhet**. Det ska gå att följa varorna från ingående handling – denna handling måste inte vara en handling från Tullverket utan kan vara en transporthandling, ett manifest, en transiteringhandling eller liknande som tillhandahålls av en annan ekonomisk aktör. Det ska gå att se alla händelser som sker under lagringen fram till dess den tillfälliga lagringen avslutas. Det betyder också att bokföringen alltid ska följa **god redovisningssed**."

> "Bokföring i lämplig form enligt artikel 148.4 i tullkodexen ska vara en bokföring i en form som är godkänd av Tullverket. En lämplig form anses bland annat innefatta krav på **spårbarhet** och att inga ändringar ska kunna göras utan att de lämnar spår i **bokföringen**." 


### Inledning
___

eSpedsg Tillfällig lagring syftar till att uppfylla ovan krav från Tullverket. Detta ger ett system som till stor del påminner om ett bokföringssystem.

Det betyder också att **inga** ändringar kan göras i sparad information, utan en `Rättelse` behöver göras.

Tillfällig lagring innefattar 3 huvudbegrepp:
- Inlägg
- Uttag
- Rättelse

Vid ankomst av material görs ett `Inlägg`.
Vid uttag av material görs ett `Uttag`.
Vid eventuella fel i befintliga `Inlägg` eller  `Uttag` görs en `Rättelse`.

### Bokföring
___

Bokföringsfliken är en sök- och arbetsvy. 

En sökvy för alla registerade `Inlägg`, `Uttag` och `Rättelse`.
En arbetsvy för `Uttag` och `Rättelse`.


### Skapa nytt inlägg
___

Vid ankomst av material klicka på `Skapa nytt inlägg`.

![Alt text](/Users/fredrikmoller/Temp/inlagg.png)

- Godsnummer behöver börja med godslokalkod. Format: <Godslokalkod><år>-<löpnummer>. 10 tecken.

Alla fält med en röd * innan ledtext är obligatoriska.

  
### Uttag
___

Vid uttag av material klicka på uttags-ikonen ![Alt text](/Users/fredrikmoller/git/espedsgtds/WebContent/WEB-INF/resources/images/unloading.png) för aktuellt `Inlägg`

![Alt text](/Users/fredrikmoller/Temp/uttag.png)

Alla fält med en röd * innan ledtext är obligatoriska.

När saldot är 0 är uttags-ikonen inte tillgänglig.


### Rättelse
___

Vid eventuella fel i `Inlägg` eller  `Uttag` görs en `Rättelse`.

Klicka på rättelse-ikonen för aktuellt `Inlägg` eller  `Uttag`.

_Exempel för Inlägg_:
![Alt text](/Users/fredrikmoller/Temp/rattelse.png)

`Rättelse` kan göras flera gånger på samma `Inlägg` och `Uttag`.


### Skapa rapport
___

> "Bokföringen ska innehålla den information och de uppgifter som gör det möjligt för Tullverket att övervaka tillståndet, särskilt när det gäller att identifiera de varor som lagras på anläggningen, deras tullstatus och befordran av dessa varor."


Sök fram aktuella poster, se **Bokföring**

Klicka på knappen **Skapa rapport**.

En PDF med namn _Tillfällig lagring.pdf_ genereras och lagras där din webläsare laddar ner från Internet.

Denna genererade rapport ska vid tillfälle skickas till Tullverket.


### Välj kolumner / Skriv ut

Det är möjligt att få ut information om bokföringen via ditt IT-systems __Skriv ut__-funktion.

1. För att få ut valfritt data; välj önskade kolumner under knappen **Välj kolumner**
2. Det som syns på skärmen kan nu skrivas ut genom ditt IT-systems skrivarfunktion. Kontakta din interna IT support om du undrar hur just din __Skriv ut__-funktion fungerar.

