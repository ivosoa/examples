int count = 0

buildXml {
  setDoubleQuotes(true)//method from MarkupBuilder
  
  mkp.xmlDeclaration(version: "1.5", encoding: "utf-8")
  documentRoot(uuid:generateUUID()){
    infos {
      title 'list of items'
      number input.item.size()
    }
    itemlist(value:'items') {
      list {
        input.item.each {
          def i = it
          "content_${count++}" (id:i.id.text(), i.name.text())
        }
      }
    }
  }
}

def generateUUID(){
  UUID.randomUUID().toString()
}