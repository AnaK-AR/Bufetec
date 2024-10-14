package com.example.bufetec.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel

data class LibraryItem(val title: String, val pdfUrl: String)

class BibliotecaViewModel : ViewModel() {

    // Lista de elementos para la biblioteca
    private val _libraryItems = mutableStateListOf<LibraryItem>()

    // Función para obtener los elementos de la biblioteca
    fun getLibraryItems(): SnapshotStateList<LibraryItem> {
        if (_libraryItems.isEmpty()) {
            _libraryItems.addAll(
                listOf(
                    LibraryItem(
                        "CÓDIGO CIVIL PARA EL ESTADO DE NUEVO LEÓN",
                        "https://www.hcnl.gob.mx/trabajo_legislativo/leyes/pdf/CODIGO%20CIVIL" +
                                "%20PARA%20EL%20ESTADO%20DE%20NUEVO%20LEON.pdf?2024-01-24"
                    ),
                    LibraryItem(
                        "CÓDIGO DE PROCEDIMIENTOS CIVILES DEL ESTADO DE NUEVO LEÓN",
                        "https://www.hcnl.gob.mx/trabajo_legislativo/leyes/pdf/CODIGO" +
                                "%20DE%20PROCEDIMIENTOS%20CIVILES%20DEL%20ESTADO%20DE%20NUEVO%20LEON.pdf?2022-06-10"
                    ),
                    LibraryItem(
                        "CONSTITUCIÓN POLÍTICA DE LOS ESTADOS UNIDOS MEXICANOS",
                        "https://www.diputados.gob.mx/LeyesBiblio/pdf/CPEUM.pdf"
                    ),
                    LibraryItem(
                        "CÓDIGO NACIONAL DE PROCEDIMIENTOS CIVILES Y FAMILIARES",
                        "https://www.diputados.gob.mx/LeyesBiblio/pdf/CNPCF.pdf"
                    ),
                    LibraryItem(
                        "CONVENCIÓN AMERICANA SOBRE DERECHOS HUMANOS (PACTO DE SAN JOSÉ)",
                        "https://tecmx-my.sharepoint.com/personal/veronica_gr_tec_mx/Documents/Microsoft%20Teams%20" +
                                "Chat%20Files/1969_Convenci%C3%B3n_Americana_sobre_Derechos_Humanos.pdf"
                    ),
                    LibraryItem(
                        "LEY GENERAL DE LOS DERECHOS DE NIÑAS, NIÑOS Y ADOLESCENTES",
                        "https://tecmx-my.sharepoint.com/personal/veronica_gr_tec_mx/Documents/" +
                                "Microsoft%20Teams%20Chat%20Files/LGDNNA.pdf"
                    )
                    ,
                    LibraryItem(
                        "LEY GENERAL DE LOS DERECHOS DE NIÑAS, NIÑOS Y ADOLESCENTES",
                        "https://tecmx-my.sharepoint.com/personal/veronica_gr_tec_mx/Documents/" +
                                "Microsoft%20Teams%20Chat%20Files/LGDNNA.pdf"
                    ),
                    LibraryItem(
                        "LEY DE LOS DERECHOS DE NIÑAS, NIÑOS Y ADOLESCENTES PARA EL ESTADO DE NUEVO LEÓN",
                        "https://www.hcnl.gob.mx/trabajo_legislativo/leyes/pdf/LEY%20DE%20LOS%20DERECHOS%20DE%20NINAS%20NINOS%20Y%20ADOLESCENTES%20PARA%20EL%20ESTADO%20DE%20NUEVO%20LEON.pdf?2023-02-24"
                    ),
                    LibraryItem(
                        "LEY DEL REGISTRO CIVIL PARA EL ESTADO DE NUEVO LEÓN",
                        "https://www.hcnl.gob.mx/trabajo_legislativo/leyes/pdf/LEY%20DEL%20REGISTRO%20CIVIL%20PARA%20EL%20ESTADO%20DE%20NUEVO%20LEON.pdf?2018-01-%208"
                    ),
                    LibraryItem(
                        "LEY REGLAMENTARIA DEL REGISTRO PÚBLICO DE LA PROPIEDAD Y DEL COMERCIO PARA EL ESTADO DE NUEVO LEÓN",
                        "https://www.hcnl.gob.mx/trabajo_legislativo/leyes/pdf/LEY%20REGLAMENTARIA%20DEL%20REGISTRO%20PUBLICO%20DE%20LA%20PROPIEDAD%20Y%20EL%20COMERCIO%20PARA%20EL%20ESTADO%20DE%20NUEVO%20LEON.pdf?2018-05-%204"
                    ),
                    LibraryItem(
                        "CONVENCIÓN SOBRE LOS DERECHOS DEL NIÑO",
                        "https://tecmx-my.sharepoint.com/personal/veronica_gr_tec_mx/Documents/Microsoft%20Teams%20Chat%20Files/derechos.pdf"
                    ),
                    LibraryItem(
                        "CONVENCIÓN INTERAMETICANA PARA PREVENIR, SANCIONAR Y ERRADICAR LA VIOLENCIA CONTRA LA MUJER (CONVENCIÓN DE BELÉM DO PARÁ)",
                        "https://tecmx-my.sharepoint.com/personal/veronica_gr_tec_mx/Documents/Microsoft%20Teams%20Chat%20Files/convencion_BelemdoPara.pdf"
                    ),
                    LibraryItem(
                        "LEY DE LOS DERECHOS DE LAS PERSONAS ADULTAS MAYORES",
                        "https://www.gob.mx/cms/uploads/attachment/file/175189/245_221116.pdf"
                    )
                    ,
                    LibraryItem(
                        "DESAPARECIDOS - DERECHOS RELACIONADOS CON LA DESAPARICIÓN DE  PERSONAS",
                        "https://www.cndh.org.mx/derechos-humanos/desaparecidos-derechos-relacionados-con-la-desaparicion-de-personas"
                    )
                    ,
                    LibraryItem(
                        "¿CUÁLES SON LOS DERECHOS HUMANOS?",
                        "https://www.cndh.org.mx/derechos-humanos/cuales-son-los-derechos-humanos"
                    )
                    ,
                    LibraryItem(
                        "DERECHOS DE LAS NIÑAS, NIÑOS Y ADOLESCENTES",
                        "https://www.cndh.org.mx/derechos-humanos/derechos-de-las-ninas-ninos-y-adolescentes"
                    )
                )
            )
        }
        return _libraryItems
    }
}