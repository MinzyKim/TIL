library(shiny)

ui <- fluidPage(

    titlePanel("Old Faithful Geyser Data"),

    
    sidebarLayout(
        sidebarPanel(
            selectInput(inputId="xAxis", "Choose X axis:", 
                        choices=c("mpg", "disp", "hp", "drat","wt")
            ),
            selectInput(inputId="yAxis", "Choose Y axis:", 
                        choices=c("wt", "drat", "hp",  "disp","mpg"  )
            ),
            selectInput(inputId="pch", "Choose shape:", 
                        choices=c("circle"=1, "circle2"=16, "square"=22  )
            ),
            sliderInput(inputId="cex", "point size", 
                        min=0.1 , max=3, value=1
            )
        ),
        # Show a plot of the generated distribution
        mainPanel(
            plotOutput(outputId="mtcarsPlot")
        )
    )
)

server <- function(input, output) {
    output$mtcarsPlot <- renderPlot ({
        title <- paste(input$xAxis, "VS", input$yAxis)
        plot(mtcars[, c(input$xAxis, input$yAxis)],
             main=title,
             pch=as.numeric(input$pch),
             cex=input$cex)
    })
}

 
shinyApp(ui = ui, server = server)
