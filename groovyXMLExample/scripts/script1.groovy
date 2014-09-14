int count = 0

buildXml {
  mkp.xmlDeclaration(version: "1.5", encoding: "utf-8")
  documentRoot(uuid:generateUUID()){
    infos {
      title 'list of items'
      number input.item.size()
    }
    itemlist(value:'items') {
      list {
        input.item.each {
          "content_${count++}" it.name.text()
        }
      }
    }
  }
}

def generateUUID(){
  UUID.randomUUID().toString()
}