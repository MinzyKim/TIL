from django.shortcuts import render, redirect
from .models import Question, Choice

# Create your views here.
def index(request):
    questions = Question.objects.all()

    context = {
        'questions': questions
    }
    return render(request, 'crudtest/index.html', context)

def new(request):
    if request.method == "POST":
        question = Question(title=request.POST.get('title'))
        question.save()
        return redirect('crudtest:index')
    else:
        return render(request, 'crudtest/new.html')

def detail(request, id):
    ques = Question.objects.get(id=id)
    surveys=ques.choice_set.all()

    context ={
        'ques':ques,
        'surveys':surveys
    }

    return render(request, 'crudtest/detail.html', {'ques':ques}, context)

def update(request, id):
    ques= Question.objects.get(id=id)

    if(request.method == "POST"):
        ques.title=request.POST.get('title')
        ques.save()
        return redirect("crudtest:detail", ques.id)
    else:
        return render(request, 'crudtest/update.html', {'ques':ques})

def delete(request,id):
    ques = Question.objects.get(id=id)

    if request.method == "POST":
        ques.delete()
        return redirect('crudtest:index')
    else:
        # GET인경우 상세정보 페이지
        return redirect('crudtest:detail', ques.id)

def survey(request, id):
    question=Question.objects.get(id=id)

    if request.method=="POST":
        text=request.POST.get("survey")
        s = Choice()
        s.survey=text
        s.question = question
        s.save()
        return redirect('survey:detail', question.id)

def survey_edit(request,c_id):
    survey=Choice.objects.get(id=c_id)
    if request.method == "POST":
        text=reqeust.POST.get('survey')
        survey.survey = text
        survey.save()
        return redirect('survey:detail', survey.question_id)
    
    else:
        context={
        "survey":survey
        }
    return render(request, 'survey/sur_edit.html', context)